/*
MIT License

Copyright (c) 2020 www.joolun.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.wyg.wechat.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyg.common.core.utils.file.FileUtils;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.wechat.config.WxMpConfiguration;
import com.wyg.wechat.domain.ImageManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ????????????
 *
 * @author www.joolun.com
 * @date 2019-03-23 21:26:35
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxmaterial")
public class WxMaterialController extends BaseController {

//	private final WxMpService wxService;

	@Autowired
	private WxMpConfiguration wxMpConfiguration;

	/**
	 * ???????????????????????????
	 * @param mulFile
	 * @param mediaType
	 * @return
	 */
	@PostMapping("/materialFileUpload")
	//	@PreAuthorize("wxmp:wxmaterial:add")
	public AjaxResult materialFileUpload(@RequestParam("file") MultipartFile mulFile,
										 @RequestParam("title") String title,
										 @RequestParam("introduction") String introduction,
										 @RequestParam("mediaType") String mediaType) {
		try {
			WxMpMaterial material = new WxMpMaterial();
			material.setName(mulFile.getOriginalFilename());
			if(WxConsts.MediaFileType.VIDEO.equals(mediaType)){
				material.setVideoTitle(title);
				material.setVideoIntroduction(introduction);
			}
			File file = FileUtils.multipartFileToFile(mulFile);
			material.setFile(file);

			// ???????????????????????????id??????????????????????????????
			WxMpService wxService = wxMpConfiguration.wxMpService();

			WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
			WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpMaterialService.materialFileUpload(mediaType,material);
			WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem wxMpMaterialFileBatchGetResult = new WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem();
			wxMpMaterialFileBatchGetResult.setName(file.getName());
			wxMpMaterialFileBatchGetResult.setMediaId(wxMpMaterialUploadResult.getMediaId());
			wxMpMaterialFileBatchGetResult.setUrl(wxMpMaterialUploadResult.getUrl());
			return AjaxResult.success(wxMpMaterialFileBatchGetResult);
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("?????????????????????????????????" + e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("????????????", e);
			return AjaxResult.error(e.getLocalizedMessage());
		}
	}

	/**
	 * ??????????????????
	 * @param data
	 * @return
	 */
	@PostMapping("/materialNews")
	@RequiresPermissions("wxmp:wxmaterial:add")
	public AjaxResult materialNewsUpload(@RequestBody JSONObject data) {
		try {
			JSONArray jSONArray = data.getJSONArray("articles");
			List<WxMpNewsArticle> articles = jSONArray.toList(WxMpNewsArticle.class);
			WxMpMaterialNews news = new WxMpMaterialNews();
			news.setArticles(articles);

			// ???????????????????????????id??????????????????????????????
			WxMpService wxService = wxMpConfiguration.wxMpService();

			WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
			WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpMaterialService.materialNewsUpload(news);
			return AjaxResult.success(wxMpMaterialUploadResult);
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("??????????????????" + e.getMessage());
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("??????????????????", e);
			return AjaxResult.error(e.getLocalizedMessage());
		}
	}

	/**
	 *??????????????????
	 * @param data
	 * @return
	 */
	@PutMapping("/materialNews")
	@RequiresPermissions("wxmp:wxmaterial:edit")
	public AjaxResult materialNewsUpdate(@RequestBody JSONObject data) {
		try {
			String mediaId = data.getStr("mediaId");
			JSONArray jSONArray = data.getJSONArray("articles");
			List<WxMpNewsArticle> articles = jSONArray.toList(WxMpNewsArticle.class);

			// ???????????????????????????id??????????????????????????????
			WxMpService wxService = wxMpConfiguration.wxMpService();

			WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
			WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate = new WxMpMaterialArticleUpdate();
			wxMpMaterialArticleUpdate.setMediaId(mediaId);
			int index = 0;
			for(WxMpNewsArticle article : articles){
				wxMpMaterialArticleUpdate.setIndex(index);
				wxMpMaterialArticleUpdate.setArticles(article);
				wxMpMaterialService.materialNewsUpdate(wxMpMaterialArticleUpdate);
				index++;
			}
			return AjaxResult.success();
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("??????????????????" + e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("??????????????????", e);
			return AjaxResult.error(e.getLocalizedMessage());
		}
	}

	/**
	 * ????????????????????????????????????URL
	 * @param mulFile
	 * @return
	 */
	@PostMapping("/newsImgUpload")
	//	@RequiresPermissions("wxmp:wxmaterial:add")
	public String newsImgUpload(@RequestParam("file") MultipartFile mulFile) throws Exception {
		File file = FileUtils.multipartFileToFile(mulFile);

		// ???????????????????????????id??????????????????????????????
		WxMpService wxService = wxMpConfiguration.wxMpService();

		WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
		WxMediaImgUploadResult wxMediaImgUploadResult = wxMpMaterialService.mediaImgUpload(file);
		Map<Object, Object> responseData = new HashMap<>();
		responseData.put("link", wxMediaImgUploadResult.getUrl());
		return JSONUtil.toJsonStr(responseData);
	}

	/**
	 * ??????id??????????????????
	 * @param
	 * @return R
	 */
	@DeleteMapping
	@RequiresPermissions("wxmp:wxmaterial:del")
	public AjaxResult materialDel(String id){

		// ???????????????????????????id??????????????????????????????
		WxMpService wxService = wxMpConfiguration.wxMpService();

		WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
		try {
			return  AjaxResult.success(wxMpMaterialService.materialDelete(id));
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("????????????????????????", e);
			return AjaxResult.error(e.getMessage());
		}
	}

	/**
	* ????????????
	* @param page ????????????
	* @param type
	* @return
	*/
	@GetMapping("/page")
	@RequiresPermissions("wxmp:wxmaterial:index")
	public AjaxResult getWxMaterialPage(Page page, String type) {
		try {

			// ???????????????????????????id??????????????????????????????
			WxMpService wxService = wxMpConfiguration.wxMpService();

			WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
		  int count = (int)page.getSize();
		  int offset = (int)page.getCurrent()*count-count;
		  if(WxConsts.MaterialType.NEWS.equals(type)){
			  return  AjaxResult.success(wxMpMaterialService.materialNewsBatchGet(offset,count));
		  }else{
			  return  AjaxResult.success(wxMpMaterialService.materialFileBatchGet(type,offset,count));
		  }
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("??????????????????", e);
			return AjaxResult.error(e.getMessage());
		}
	}

	/**
	 * ????????????2
	 * @param type
	 * @return
	 */
	@GetMapping("/page-manager")
//	@RequiresPermissions("wxmp:wxmaterial:index")
	public String getWxMaterialPageManager(Integer count, Integer offset, String type) throws WxErrorException {
		List<ImageManager> listImageManager = new ArrayList<>();

		// ???????????????????????????id??????????????????????????????
		WxMpService wxService = wxMpConfiguration.wxMpService();

		WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
		List<WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem> list = wxMpMaterialService.materialFileBatchGet(type,offset,count).getItems();
		list.forEach(wxMaterialFileBatchGetNewsItem -> {
			ImageManager imageManager = new ImageManager();
			imageManager.setName(wxMaterialFileBatchGetNewsItem.getMediaId());
			imageManager.setUrl(wxMaterialFileBatchGetNewsItem.getUrl());
			imageManager.setThumb(wxMaterialFileBatchGetNewsItem.getUrl());
			listImageManager.add(imageManager);
		});
		return JSONUtil.toJsonStr(listImageManager);
	}

	/**
	* ????????????????????????
	* @param
	* @return R
	*/
	@GetMapping("/materialVideo")
	@RequiresPermissions("wxmp:wxmaterial:get")
	public AjaxResult getMaterialVideo(String mediaId){

		// ???????????????????????????id??????????????????????????????
		WxMpService wxService = wxMpConfiguration.wxMpService();

		WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
	  try {
		  return  AjaxResult.success(wxMpMaterialService.materialVideoInfo(mediaId));
	  } catch (WxErrorException e) {
		  e.printStackTrace();
		  log.error("??????????????????????????????", e);
		  return AjaxResult.error(e.getMessage());
	  }
	}

	/**
	 * ??????????????????????????????
	 * @param
	 * @return R
	 */
	@GetMapping("/materialOther")
	@RequiresPermissions("wxmp:wxmaterial:get")
	public ResponseEntity<byte[]> getMaterialOther(String mediaId, String fileName) throws Exception {
		try {

			// ???????????????????????????id??????????????????????????????
			WxMpService wxService = wxMpConfiguration.wxMpService();

			WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
			//????????????
			InputStream is = wxMpMaterialService.materialImageOrVoiceDownload(mediaId);
			byte[] body = new byte[is.available()];
			is.read(body);
			HttpHeaders headers = new HttpHeaders();
			//??????????????????
			headers.add("Content-Disposition", "attchement;filename=" +  URLEncoder.encode(fileName, "UTF-8"));
			headers.add("Content-Type", "application/octet-stream");
			HttpStatus statusCode = HttpStatus.OK;
			//????????????
			ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
			return entity;
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("????????????????????????????????????", e);
			return null;
		}
	}

	/**
	 * ????????????????????????????????????
	 * @param
	 * @return R
	 */
	@GetMapping("/tempMaterialOther")
	@RequiresPermissions("wxmp:wxmsg:index")
	public ResponseEntity<byte[]> getTempMaterialOther(String mediaId, String fileName) throws Exception {
		try {

			// ???????????????????????????id??????????????????????????????
			WxMpService wxService = wxMpConfiguration.wxMpService();

			WxMpMaterialService wxMpMaterialService = wxService.getMaterialService();
			//????????????
			InputStream is = new FileInputStream(wxMpMaterialService.mediaDownload(mediaId));
			byte[] body = new byte[is.available()];
			is.read(body);
			HttpHeaders headers = new HttpHeaders();
			//??????????????????
			headers.add("Content-Disposition", "attchement;filename=" +  URLEncoder.encode(fileName, "UTF-8"));
			headers.add("Content-Type", "application/octet-stream");
			HttpStatus statusCode = HttpStatus.OK;
			//????????????
			ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
			return entity;
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error("????????????????????????????????????", e);
			return null;
		}
	}
}
