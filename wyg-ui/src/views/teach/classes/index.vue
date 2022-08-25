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

      <!--专业数据-->
      <el-col :span="this.showDeptTree?20:24" :xs="24">

        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="班级名称" prop="className">
            <el-input
              v-model="queryParams.className"
              placeholder="请输入班级名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="班级编号" prop="classCode">
            <el-input
              v-model="queryParams.classCode"
              placeholder="请输入班级编号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="所属专业" prop="classCode">
            <el-select v-model="queryParams.majorId" placeholder="请选择所属专业">
              <el-option
                v-for="major in queryMajorList"
                :key="major.id"
                :label="major.majorName"
                :value="major.id"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="当前阶段" prop="stage">
            <el-input
              v-model="queryParams.stage"
              placeholder="请输入当前阶段"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="班主任" prop="teacherId">
            <el-input
              v-model="queryParams.teacherId"
              placeholder="请输入班主任ID"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="开班时间" prop="startTime">
            <el-date-picker clearable
                            v-model="queryParams.startTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择开班时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结业时间" prop="endTime">
            <el-date-picker clearable
                            v-model="queryParams.endTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择结业时间">
            </el-date-picker>
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
              v-hasPermi="['teach:classes:add']"
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
              v-hasPermi="['teach:classes:edit']"
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
              v-hasPermi="['teach:classes:remove']"
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
              v-hasPermi="['teach:classes:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="classesList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="ID" align="center" prop="id" width="60"/>
          <el-table-column label="班级名称" align="center" prop="className"/>
<!--          <el-table-column label="班级类型" align="center" prop="classType">-->
<!--            <template slot-scope="scope">-->
<!--              <dict-tag :options="dict.type.teach_class_types" :value="scope.row.classType"/>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column label="专业" align="center" prop="major.majorName" width="120"/>
          <el-table-column label="当前阶段" align="center" prop="stage"/>
          <el-table-column label="班主任" align="center" prop="teacher.teacherName"/>
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.teach_class_status" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="在读人数" align="center" prop="readingCount"/>
          <el-table-column label="退学人数" align="center" prop="dropOutCount"/>
          <el-table-column label="休学人数" align="center" prop="absenceCount"/>
          <el-table-column label="离校人数" align="center" prop="levelCount"/>
          <el-table-column label="毕业人数" align="center" prop="graduateCount"/>
          <el-table-column label="就业人数" align="center" prop="employmentCount"/>

          <el-table-column label="开班时间" align="center" prop="startTime" width="120">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="结业时间" align="center" prop="endTime" width="120">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>


          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">


              <el-dropdown>
                <span class="el-dropdown-link">
                  操作<i class="el-icon-caret-bottom el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-hasPermi="['teach:classes:edit']">
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="handleUpdate(scope.row)"
                    >修改
                    </el-button>
                  </el-dropdown-item>
                  <el-dropdown-item v-hasPermi="['teach:classes:edit']">
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="handleClassStage(scope.row)"
                    >详情
                    </el-button>
                  </el-dropdown-item>
                  <el-dropdown-item v-hasPermi="['teach:classes:remove']">
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                      @click="handleDelete(scope.row)"
                    >删除
                    </el-button>
                  </el-dropdown-item>

                </el-dropdown-menu>
              </el-dropdown>


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

    <!-- 添加或修改班级信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属院校" prop="collegeId">
          <treeselect v-model="form.collegeId " :options="deptOptions" :show-count="true"
                      placeholder="请选择所属院校" @select="handleCollegeSelected"
                      :disabled="form.id && form.status!==teachClassStatus.Waiting.code"/>
        </el-form-item>
        <el-form-item label="所属专业" prop="majorId">
          <el-select v-model="form.majorId" placeholder="请选择所属专业" @change="getStageList"
                     :disabled="form.id && form.status!==teachClassStatus.Waiting.code">
            <el-option
              v-for="major in dialogMajorList"
              :key="major.id"
              :label="major.majorName"
              :value="major.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="form.className" placeholder="请输入班级名称"
                    :disabled="form.id && form.status!==teachClassStatus.Waiting.code"/>
        </el-form-item>
        <el-form-item label="班级编号" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入班级编号"
                    :disabled="form.id && form.status!==teachClassStatus.Waiting.code"/>
        </el-form-item>
        <el-form-item label="班主任" prop="teacherId">
          <el-input v-model="selectedTeacherName" placeholder="请选择班主任" @click.native="showTeacherSelector"/>
        </el-form-item>
        <el-form-item label="当前阶段" prop="stage">
          <el-select v-model="form.stage" placeholder="请选择当前阶段" clearable>
            <el-option
              v-for="stage in stageList"
              :key="stage.stageName"
              :label="stage.stageName"
              :value="stage.stageName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班级类型" prop="classType">
          <el-select v-model="form.classType" placeholder="请选择学历" clearable>
            <el-option
              v-for="dict in dict.type.teach_class_types"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班级状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择班级状态" clearable>
            <el-option
              v-for="dict in dict.type.teach_class_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="开班时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开班时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="班级详情" :visible.sync="openStage" width="80%" append-to-body>
      <class-details :class-info="currentClass"/>
    </el-dialog>

    <teacher-selector ref="teacherSelector" @select="handleTeacherSelected"/>
  </div>
</template>

<script>
  import {listClasses, getClasses, delClasses, addClasses, updateClasses} from "@/api/teach/classes";
  import {listClassStage, getClassStage, delClassStage, addClassStage, updateClassStage} from "@/api/teach/classStage";
  import {listStage} from "@/api/teach/stage";
  import {listMajor} from "@/api/teach/major";
  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import classDetails from "./classDetails";
  import {isSingleNode} from '@/utils/util'

  import teacherSelector from "@/views/teach/teacher/teacherSelector"
  import {teachClassStatus} from '@/utils/constant'

  export default {
    name: "Classes",
    dicts: ['teach_class_types', 'teach_class_status', 'teach_stage_status'],
    components: {Treeselect, teacherSelector, classDetails},
    data() {
      return {
        // 遮罩层
        loading: true,
        openStage: false,

        teachClassStatus: {...teachClassStatus},


        // 院校名称
        deptName: null,
        // 院校树选项
        deptOptions: [],
        defaultProps: {
          children: "children",
          label: "label"
        },
        showDeptTree: true,

        // 查询参数
        majorQueryParams: {
          collegeId: null,
        },
        queryMajorList: [],
        dialogMajorList: [],
        selectedTeacherName: null,

        // 学期阶段表格数据
        stageList: [],
        // 查询参数
        stageQueryParams: {
          stageName: null,
          stageCode: null,
          eduSystem: null,
          collegeId: null,
          status: null,
        },


        currentClass: {
          id: 0,
          major: {
            majorName: null,
          },
          stage: {
            stageName: null,
          }
        },

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
        // 班级信息表格数据
        classesList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          className: null,
          classCode: null,
          classType: null,
          readingCount: null,
          dropOutCount: null,
          absenceCount: null,
          employmentCount: null,
          stage: null,
          startTime: null,
          endTime: null,
          collegeId: null,
          majorId: null,
          teacherId: null,
          status: null,

        },
        // 查询参数
        classStagequeryParams: {
          pageNum: 1,
          pageSize: 10,
          classId: null,
          stageId: null,
          startTime: null,
          endTime: null,
          status: null,
        },
        // 表单参数
        form: {collegeId: null, teacherName: null},
        // 表单校验
        rules: {
          collegeId: [
            {required: true, message: "请选择所属院校", trigger: "blur"}
          ],
          majorId: [
            {required: true, message: "请选择所属专业", trigger: "blur"}
          ],
          className: [
            {required: true, message: "请输入班级名称", trigger: "blur"}
          ],

        }
      };
    },
    watch: {
      // 根据名称筛选院校树
      collegeName(val) {
        this.$refs.tree.filter(val);
      },

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
        this.majorQueryParams.collegeId = data.id;
        this.form.collegeId = data.id;
        this.handleQuery();
        this.getQueryMajorList();
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.deptOptions = response.data;
          this.showDeptTree = !isSingleNode(response.data);
        });
      },

      /** 查询专业信息列表 */
      getQueryMajorList() {
        this.queryMajorList = [];
        listMajor(this.majorQueryParams).then(response => {
          this.queryMajorList = response.rows;
        });
      },

      getDialogMajorList() {
        this.dialogMajorList = [];
        this.majorQueryParams.collegeId = this.form.collegeId;
        listMajor(this.majorQueryParams).then(response => {
          this.dialogMajorList = response.rows;
        });
      },

      /** 查询学期阶段列表 */
      getStageList() {
        this.stageList = [];
        if (!this.form.majorId) {
          return;
        }
        this.stageQueryParams.collegeId = this.form.collegeId;
        for (var i = 0; i < this.dialogMajorList.length; i++) {
          var major = this.dialogMajorList[i];
          if (major.id == this.form.majorId) {
            this.stageQueryParams.eduSystem = major.majorType;
            break;
          }
        }

        listStage(this.stageQueryParams).then(response => {
          this.stageList = response.rows;
        });
      },

      /** 查询班级信息列表 */
      getList() {
        this.loading = true;
        listClasses(this.queryParams).then(response => {
          this.classesList = response.rows;
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
          className: null,
          classCode: null,
          classType: null,
          readingCount: null,
          dropOutCount: null,
          absenceCount: null,
          employmentCount: null,
          stage: null,
          startTime: null,
          endTime: null,
          collegeId: null,
          majorId: null,
          teacherId: null,
          status: null,
          remark: null,
          delFlag: null,
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
        this.title = "添加班级信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getClasses(id).then(response => {
          this.form = response.data;
          this.selectedTeacherName = response.data.teacher.teacherName;
          this.stageQueryParams.collegeId = response.data.collegeId;
          this.getStageList();
          this.open = true;
          this.title = "修改班级信息";
          this.form.majorId = response.data.majorId;

        });
      },


      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateClasses(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addClasses(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除班级信息编号为"' + ids + '"的数据项？').then(function () {
          return delClasses(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/classes/export', {
          ...this.queryParams
        }, `classes_${new Date().getTime()}.xlsx`)
      },

      handleCollegeSelected(node, id) {
        //this.selectedCollegeName = node.label;
        this.$refs.teacherSelector.collegeName = node.label;
        this.$refs.teacherSelector.queryParams.collegeId = node.id;
        this.stageQueryParams.collegeId = node.id;

        this.form.majorId = null;
        this.getDialogMajorList();
      },

      showTeacherSelector() {

        if (!this.form.collegeId) {
          this.$message({
            message: '请先选择所属院校！',
            type: 'warning'
          });
          return;
        }

        this.$refs.teacherSelector.show();
      },
      handleTeacherSelected(teacher) {
        this.form.teacherId = teacher.id;
        this.form.teacherName = teacher.teacherName;
        this.selectedTeacherName = teacher.teacherName;
      },


      handleClassStage(row) {
        const id = row.id || this.ids;
        getClasses(id).then(response => {
          this.currentClass = response.data;
          this.openStage = true;
        });
      },


    }
  };
</script>
