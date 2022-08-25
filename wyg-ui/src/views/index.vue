<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning" style="font-size: 14px">
          领取阿里云通用云产品优惠券
          <br />
          <el-link
            href="https://www.aliyun.com/minisite/goods?userCode=hd9sngi1&share_source=copy_link"
            type="primary"
            target="_blank"
            >https://www.aliyun.com/minisite/goods?userCode=hd9sngi1&share_source=copy_link</el-link
          >
          <br />
          领取腾讯云通用云产品优惠券
          <br />
          <el-link
            href="https://cloud.tencent.com/act/cps/redirect?redirect=35834&cps_key=af59af0abad1f7fa52d35a6e931825d1"
            type="primary"
            target="_blank"
            >https://cloud.tencent.com/act/cps/redirect?redirect=35834&cps_key=af59af0abad1f7fa52d35a6e931825d1</el-link
          >
          <br />
          阿里云服务器折扣区
          <el-link href="https://www.aliyun.com/minisite/goods?source=5176.11533457&userCode=hd9sngi1" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          >
          &nbsp;&nbsp;&nbsp; 腾讯云服务器秒杀区
          <el-link href="https://cloud.tencent.com/act/cps/redirect?redirect=2446&cps_key=af59af0abad1f7fa52d35a6e931825d1&from=console" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          ><br />
          <h4 class="text-danger">
            云产品通用红包，可叠加官网常规优惠使用。(仅限新用户)
          </h4>
        </blockquote>

        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>无忧果教学考务系统</h2>
        <p>
          基于及结合
          <el-link href="http://doc.ruoyi.vip/ruoyi-cloud/" type="primary" target="_blank">若依微服务框架</el-link>
          开发的
          <el-link href="https://gitee.com/Dincat/wyg-teach" type="primary" target="_blank">无忧果教学教务系统</el-link>，集成
          <el-link href="https://gitee.com/Dincat/wyg-exam" type="primary" target="_blank">无忧果考试平台</el-link>：
        </p>
        <p>1.  多校区/院校管理；</p>
        <p>2.  专业设置；</p>
        <p>3.  班级管理；</p>
        <p>4.  学期阶段管理；</p>
        <p>5.  课程管理；</p>
        <p>6.  班级作业；</p>
        <p>7.  班级考试；</p>
        <p>8.  考试教务（集成<el-link href="https://gitee.com/Dincat/wyg-exam" type="primary" target="_blank">无忧果考试平台</el-link>）；</p>
        <p>9.  通知公告；</p>
        <p>10.  在线办公（工作流，待实现）；</p>
        <p>11.  更多……；</p>
        <p>后续将继续完善功能，并将实现教师端、学生端APP（uni-app)。</p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://gitee.com/Dincat/wyg-teach')"
            >访问码云</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://www.WorrilessGo.com')"
            >访问主页</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>SpringCloud</li>
              <li>Nacos</li>
              <li>Sentinel</li>
              <li>Seata</li>
              <li>Minio</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Echarts</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：<el-link
                href="http://www.WorrilessGo.com"
                target="_blank"
                >http://www.WorrilessGo.com</el-link
              >
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ群：
              <a href="https://qm.qq.com/cgi-bin/qm/qr?k=b2NMgSJsEX7fAI2FJu6jjCTqzU707UZl&jump_from=webapi" target="_blank">
                <img src="https://img.shields.io/badge/747860738-blue.svg" alt="无忧果服务" title="无忧果服务">
              </a>
            </p>

          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>

            <el-collapse-item title="v1.1.0 - 2022-08-25">
              <ol>
                <li>修改部门模块为校区数据；</li>
                <li>修改DataScope注解，数据过滤处理支持跨服务（若依框架只支持在系统服务中使用）；</li>
                <li>班级作业实现，支持引用考试服务中的试卷；</li>
              </ol>
            </el-collapse-item>


            <el-collapse-item title="v1.0.0 - 2022-08-02">
              <ol>
                <li>无忧果教学考务系统发布</li>
              </ol>
            </el-collapse-item>


          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>捐赠支持</span>
          </div>
          <div class="body">
            <img
              src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/support.jpg"
              alt="donate"
              width="100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >你可以请作者喝杯咖啡表示鼓励</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "1.1.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

