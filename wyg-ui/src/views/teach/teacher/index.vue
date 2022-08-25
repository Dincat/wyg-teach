<template>
  <div class="app-container">

    <el-row :gutter="20">

      <!--院校树数据-->
      <el-col :span="this.showDeptTree?4:0" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入院校名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            highlight-current
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>

      <!--教职工数据-->
      <el-col :span="this.showDeptTree?20:24" :xs="24">

        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="姓名" prop="teacherName">
            <el-input
              v-model="queryParams.teacherName"
              placeholder="请输入教职工名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="编号" prop="teacherCode">
            <el-input
              v-model="queryParams.teacherCode"
              placeholder="请输入教职工编号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="学历" prop="education">
            <el-input
              v-model="queryParams.education"
              placeholder="请输入学历"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input
              v-model="queryParams.phone"
              placeholder="请输入电话"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="民族" prop="nation">
            <el-select v-model="queryParams.nation" placeholder="请选择民族" clearable>
              <el-option
                v-for="dict in dict.type.sys_nation"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="学历" prop="education">
            <el-select v-model="queryParams.education" placeholder="请选择学历" clearable>
              <el-option
                v-for="dict in dict.type.teach_education_bg"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['teach:teacher:add']"
            >新增
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['teach:teacher:edit']"
            >修改
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['teach:teacher:remove']"
            >删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['teach:teacher:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="ID" align="center" prop="id"/>
          <el-table-column label="姓名" align="center" prop="teacherName"/>
          <el-table-column label="编号" align="center" prop="teacherCode"/>
          <el-table-column label="身份证号" align="center" prop="idNo"/>
          <el-table-column label="学历" align="center" prop="education">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.teach_education_bg" :value="scope.row.education"/>
            </template>
          </el-table-column>
          <el-table-column label="电话" align="center" prop="phone"/>
          <el-table-column label="性别" align="center" prop="sex">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
            </template>
          </el-table-column>
          <el-table-column label="所属院校" align="center" prop="college.deptName"/>
          <el-table-column label="任教专业" align="center" prop="majorName"/>
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.teach_teacher_status" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="创建者" align="center" prop="createBy"/>
          <el-table-column label="创建时间" align="center" prop="createTime">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="更新者" align="center" prop="updateBy"/>
          <el-table-column label="更新时间" align="center" prop="updateTime">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['teach:teacher:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['teach:teacher:remove']"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />

      </el-col>

    </el-row>

    <!-- 添加或修改教职工信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" size="medium" label-width="100px">
        <el-row :gutter="15">
          <el-col :span="16">
            <el-row>
              <el-col :span="12">
                <el-form-item label="姓名" prop="teacherName">
                  <el-input v-model="form.teacherName" placeholder="请输入姓名" clearable
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="编号" prop="teacherCode">
                  <el-input v-model="form.teacherCode" placeholder="请输入教职工编号" clearable
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="身份证号" prop="idNo">
                  <el-input v-model="form.idNo" placeholder="请输入教职工身份证号" clearable
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出生日期" prop="birthday">
                  <el-date-picker v-model="form.birthday" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                  :style="{width: '100%'}" placeholder="请选择教职工出生日期" clearable></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="所属院校" prop="collegeId">
                  <treeselect v-model="form.collegeId" :options="deptOptions" :show-count="true"
                              @select="handleCollegeSelected"
                              placeholder="请选择所属院校"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="任教专业" prop="majorName">
                  <el-input v-model="selectedMajorName" placeholder="请选择任教专业" @click.native="showMajorSelector"/>
                </el-form-item>
              </el-col>

            </el-row>
          </el-col>
          <el-col :span="8">
            <el-row>
              <el-form-item label="教职工照片" prop="avatar" required>
                <imageUpload :limit="1" v-model="form.avatar"/>
                <!--                <el-upload ref="avatar"-->
                <!--                            list-type="picture-card" accept="image/*">-->
                <!--                  <i class="el-icon-plus"></i>-->
                <!--                  <div slot="tip" class="el-upload__tip">只能上传不超过 2MB 的image/*文件</div>-->
                <!--                </el-upload>-->
              </el-form-item>
            </el-row>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="民族" prop="nation">
              <el-select v-model="form.nation" placeholder="请选择民族" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="dict in dict.type.sys_nation"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择性别" clearable :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择教职工状态" clearable
                         :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.teach_teacher_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="8">
            <el-form-item label="教职工类型" prop="teacherType">
              <el-select v-model="form.teacherType" placeholder="请选择教职工类型" clearable
                         :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.teach_teacher_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="毕业院校" prop="graduateSchool">
              <el-input v-model="form.graduateSchool" placeholder="请输入教职工毕业院校" clearable
                        :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="毕业专业" prop="graduateMajor">
              <el-input v-model="form.graduateMajor" placeholder="请输入教职工毕业专业" clearable
                        :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="学历" prop="education">
              <el-select v-model="form.education" placeholder="请选择教职工学历" clearable
                         :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.teach_education_bg"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="学位" prop="degree">
              <el-select v-model="form.degree" placeholder="请选择教职工学位" clearable :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.teach_degree"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语水平" prop="englishLevel">
              <el-select v-model="form.englishLevel" placeholder="请选择教职工英语水平" clearable
                         :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.teach_english_level"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">

          <el-col :span="8">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="联系邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入联系邮箱" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="QQ" prop="QQ">
              <el-input v-model="form.qq" placeholder="请输入联系QQ" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="15">


          <el-col :span="24">
            <el-form-item label="省市区街道" prop="address">

              <region-selects @change="regionChange" :town="true" class="form-control"
                              v-model="selectedRegion"></region-selects>
            </el-form-item>
          </el-col>


          <el-col :span="24">
            <el-form-item label="详细地址" prop="address">
              <el-input v-model="form.address" type="textarea" placeholder="请输入详细地址"
                        :autosize="{minRows: 3, maxRows: 3}" :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="15">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入备注"
                      :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-row>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <major-selector ref="majorSelector" @select="handleMjaorSelected"/>
  </div>
</template>

<script>
  import {listTeacher, getTeacher, delTeacher, addTeacher, updateTeacher} from "@/api/teach/teacher";
  import {checkIdNumberExist, checkPhoneExist, checkEmailExist} from '@/api/system/user'
  import {isNotEmpty, isValidPhone, isValidIdNumber,isValidEmail} from '@/utils/util'
  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {RegionSelects} from 'v-region'
  import majorSelector from "@/views/teach/major/majorSelector"
  import {isSingleNode} from '@/utils/util'

  export default {
    name: "Teacher",
    dicts: ['sys_nation', 'teach_education_bg', 'sys_user_sex', 'teach_teacher_type', 'teach_degree', 'teach_teacher_status', 'teach_politics_status', 'teach_english_level'],
    components: {Treeselect, RegionSelects, majorSelector},
    data() {
      let checkIDNumber = (rule, value, callback) => {
        if (!isNotEmpty(value)) {
          return callback(new Error('请输入身份证号'))
        }

        if (!isValidIdNumber(value)) {
          return callback(new Error('请输入正确的二代身份证号码'))
        }

        // 检查用户名是否存在
        checkIdNumberExist(value,this.form.userId).then(response => {
          if (response.isExist) {
            callback(new Error('当前身份证号已被使用！'))
          } else {
            callback()
          }
        })
      }

      let checkRegisterEmail = (rule, value, callback) => {
        if (!isNotEmpty(value)) {
          return;
        }

        if (!isValidEmail(value)) {
          return callback(new Error('邮箱地址不正确！'))
        }

        // 检查邮箱地址是否存在
        checkEmailExist(value,this.form.userId).then(response => {
          if (response.isExist) {
            callback(new Error('该邮箱地址已被使用！'))
          } else {
            callback()
          }
        })

      }
      // 校验手机号
      let validPhone = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入电话号码'))
        } else if (!isValidPhone(value)) {
          callback(new Error('请输入正确的11位手机号码'))
        } else {

          // 检查用户名是否存在
          checkPhoneExist(value,this.form.userId).then(response => {
            if (response.isExist) {
              callback(new Error('该电话号码已被使用！'))
            } else {
              callback()
            }
          })

        }
      };

      return {
        // 遮罩层
        loading: true,

        // 院校名称
        deptName: null,
        // 院校树选项
        deptOptions: [],
        defaultProps: {
          children: "children",
          label: "label"
        },
        showDeptTree: true,


        selectedCollegeNode: null,
        selectedRegion: {},
        selectedMajorName: null,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 教职工信息表格数据
        teacherList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          teacherName: null,
          teacherCode: null,
          idNo: null,
          education: null,
          politicsStatus: null,
          phone: null,
          nation: null,
          sex: null,
          avatar: null,
          collegeId: null,
          majorId: null,
          status: null,
          createBy: null,
          updateBy: null,
        },
        // 表单参数
        form: {
          teacherName: null,
          teacherCode: null,
          idNo: null,
          birthday: null,
          collegeId: null,
          teacherType: null,
          avatar: null,
          nation: null,
          sex: null,
          status: null,
          graduateSchool: null,
          graduateMajor: null,
          majorId: null,
          education: null,
          degree: null,
          englishLevel: null,
          phone: null,
          email: null,
          qq: null,
          province: null,
          provinceKey: null,
          city: null,
          cityKey: null,
          district: null,
          districtKey: null,
          town: null,
          townKey: null,
          address: null,
          remark: null,
        },
        // 表单校验
        rules: {
          teacherName: [
            {required: true, message: "教职工名称不能为空", trigger: "blur"}
          ],
          teacherCode: [
            {required: true, message: "教职工编号不能为空", trigger: "blur"}
          ],
          status: [
            {required: true, message: "请选择教职工状态", trigger: "blur"}
          ],
          idNo: [{
            required: true,
            trigger: 'blur',
            validator: checkIDNumber
          }],
          email: [{validator: checkRegisterEmail, trigger: 'blur'}],
          phone: [{required: true, trigger: 'blur', validator: validPhone}],
        }
      };


    },
    watch: {
      // 根据名称筛选院校树
      collegeName(val) {
        this.$refs.tree.filter(val);
      },
      'form.collegeId': function (val) {
        this.form.majorId = null;
        this.form.selectedMajorName = null;
        if (this.form.collegeId == undefined) {
          this.$refs.majorSelector.collegeName = null;
          this.$refs.majorSelector.queryParams.collegeId = null;
        }
      },
      'form.idNo': function (val) {
        if(!val) return;
        this.getBirthdayFromIdCard(val);
      }
    },
    created() {
      this.getList();
      this.getTreeselect();
    },
    methods: {

      // 筛选节点
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.queryParams.params['collegeIds'] = data.descendants;
        this.handleQuery();
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.deptOptions = response.data;
          this.showDeptTree = !isSingleNode(response.data);
        });
      },

      //receive selected region data
      regionChange(data) {
        if (data.province != undefined) {
          this.form.province = data.province.value;
          this.form.provinceKey = data.province.key;
          this.form.city = null;
          this.form.cityKey = null;
          this.form.district = null;
          this.form.districtKey = null;
          this.form.town = null;
          this.form.townKey = null;
        }
        if (data.city != undefined) {
          this.form.city = data.city.value;
          this.form.cityKey = data.city.key;
          this.form.district = null;
          this.form.districtKey = null;
          this.form.town = null;
          this.form.townKey = null;
        }
        if (data.area != undefined) {
          this.form.district = data.area.value;
          this.form.districtKey = data.area.key;
          this.form.town = null;
          this.form.townKey = null;
        }
        if (data.town != undefined) {
          this.form.town = data.town.value;
          this.form.townKey = data.town.key;
        }

      },


      /** 查询教职工信息列表 */
      getList() {
        this.loading = true;
        listTeacher(this.queryParams).then(response => {
          this.teacherList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          teacherName: null,
          teacherCode: null,
          idNo: null,
          birthday: null,
          education: null,
          politicsStatus: null,
          email: null,
          phone: null,
          mail: null,
          qq: null,
          nation: null,
          province: null,
          city: null,
          address: null,
          sex: null,
          avatar: null,
          collegeId: null,
          collegeName: null,
          majorId: null,
          majorName: null,
          status: null,
          remark: null,
          delFlag: null,
          userId: null,
          tenantId: null,
          tenantCode: null,
          createId: null,
          createBy: null,
          createTime: null,
          updateId: null,
          updateBy: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加教职工信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getTeacher(id).then(response => {

          this.selectedRegion.province = response.data.provinceKey;
          this.selectedRegion.city = response.data.cityKey;
          this.selectedRegion.area = response.data.districtKey;
          this.selectedRegion.town = response.data.townKey;
          this.selectedMajorName = response.data.majorName;

          this.form = response.data;
          this.open = true;
          this.title = "修改教职工信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateTeacher(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addTeacher(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除教职工信息编号为"' + ids + '"的数据项？').then(function () {
          return delTeacher(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/teacher/export', {
          ...this.queryParams
        }, `teacher_${new Date().getTime()}.xlsx`)
      },

      handleCollegeSelected(node, id) {
        //this.selectedCollegeName = node.label;
        this.$refs.majorSelector.collegeName = node.label;
        this.$refs.majorSelector.queryParams.collegeId = node.id;
      },

      getBirthdayFromIdCard : function(idCard) {
        var birthday = "";
        if(idCard != null && idCard != ""){
          if(idCard.length == 15){
            birthday = "19"+idCard.substr(6,6);
          } else if(idCard.length == 18){
            birthday = idCard.substr(6,8);
          }

          birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
        }
        this.form.birthday=birthday;
        return birthday;
      },
      showMajorSelector() {

        if (!this.form.collegeId) {
          this.$message({
            message: '请先选择所属院校！',
            type: 'warning'
          });
          return;
        }

        this.$refs.majorSelector.show();
      },
      handleMjaorSelected(major) {
        this.form.majorId = major.id;
        this.form.majorName = major.majorName;
        this.selectedMajorName = major.majorName;
      }

    }
  };
</script>
