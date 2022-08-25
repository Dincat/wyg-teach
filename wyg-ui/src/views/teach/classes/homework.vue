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

      <!--学生数据-->
      <el-col :span="this.showDeptTree?20:24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="作业名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入作业名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="所属课程" prop="courseId">
            <el-input
              v-model="queryParams.courseId"
              placeholder="请输入所属课程"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="所属班级" prop="classId">
            <el-input
              v-model="queryParams.classId"
              placeholder="请选择所属班级"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="所属阶段" prop="stageId">
            <el-input
              v-model="queryParams.stageId"
              placeholder="请输入所属阶段"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="作业类型" prop="homeworkType">
            <el-select v-model="queryParams.homeworkType" placeholder="请选择作业类型" clearable>
              <el-option
                v-for="dict in dict.type.teach_homework_types"
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
              v-hasPermi="['teach:class:homework:add']"
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
              v-hasPermi="['teach:class:homework:edit']"
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
              v-hasPermi="['teach:class:homework:remove']"
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
              v-hasPermi="['teach:class:homework:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="class_homeworkList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="ID" align="center" prop="id"/>
          <el-table-column label="作业名称" align="center" prop="name"/>
          <el-table-column label="布置老师" align="center" prop="teacher.teacherName"/>
          <el-table-column label="所属课程" align="center" prop="course.courseName"/>
          <el-table-column label="所属班级" align="center" prop="classes.className"/>
          <el-table-column label="作业类型" align="center" prop="homeworkType">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.teach_homework_types" :value="scope.row.homeworkType"/>
            </template>
          </el-table-column>
          <el-table-column label="发布状态" align="center" prop="publishStatus">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.exam_publish_status" :value="scope.row.publishStatus"/>
            </template>
          </el-table-column>
          <el-table-column label="是否公开" align="center" prop="isPublic">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isPublic"/>
            </template>
          </el-table-column>
          <el-table-column label="完成情况" align="center" prop="studentCount">
            <template slot-scope="scope">
              <span>{{ scope.row.submitCount }} / {{ scope.row.studentCount }}</span>
            </template>
          </el-table-column>
          <el-table-column label="截止时间" align="center" prop="deadLine" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.deadLine, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>

          <el-table-column label="布置时间" align="center" prop="publishTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.publishTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleDetail(scope.row)"
                v-hasPermi="['teach:class:homework:query']"
              >详情
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-if="scope.row.publishStatus==1"
                @click="handlePublish(scope.row)"
                v-hasPermi="['teach:class:homework:edit']"
              >布置作业
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-if="scope.row.publishStatus==1"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['teach:class:homework:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                v-if="scope.row.publishStatus==1"
                @click="handleDelete(scope.row)"
                v-hasPermi="['teach:class:homework:remove']"
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

    <!-- 添加或修改班级作业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="760px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="作业名称" prop="paperId">
          <el-input v-model="form.name" placeholder="请输入作业名称"/>
        </el-form-item>

        <el-form-item label="所属院校" prop="collegeId" v-if="showDeptTree">
          <treeselect v-model="form.collegeId" :options="deptOptions" :show-count="true"
                      @select="handleCollegeSelected"
                      placeholder="请选择所属院校"/>
        </el-form-item>

        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item label="所属班级" prop="className">
              <el-input v-model="selectedClassName" placeholder="请选择所属班级" @click.native="showClassSelector"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属课程" prop="courseId">
              <el-input v-model="selectedCourseName" placeholder="请输入所属课程" @click.native="showCourseSelector"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item label="作业类型" prop="homeworkType">
              <el-select v-model="form.homeworkType" placeholder="请选择作业类型">
                <el-option
                  v-for="dict in dict.type.teach_homework_types"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="截止时间" prop="deadLine">
              <el-date-picker clearable
                              v-model="form.deadLine"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择截止时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="试卷" v-if="form.homeworkType=='paper'">
          <el-input v-model="selectedPaperName" placeholder="请选择试卷" @click.native="showPaperSelector"/>
        </el-form-item>

        <el-form-item label="作业描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容"/>
        </el-form-item>


        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item label="发布状态" prop="publishStatus">
              <el-radio-group v-model="form.publishStatus">
                <el-radio
                  v-for="dict in dict.type.exam_publish_status"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否公开" prop="isPublic">
              <el-radio-group v-model="form.isPublic">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item label="合格分数" prop="passScore">
              <el-input-number v-model="form.passScore" placeholder="请输入合格分数"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="良分数" prop="fineScore">
              <el-input-number v-model="form.fineScore" placeholder="请输入良分数"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item label="优分数" prop="goodScore">
              <el-input-number v-model="form.goodScore" placeholder="请输入优分数"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="满分分数" prop="fullScore">
              <el-input-number v-model="form.fullScore" placeholder="请输入满分"/>
            </el-form-item>
          </el-col>
        </el-row>


        <el-form-item label="附件" prop="remark">

          <FileUpload :limit="5" v-model="form.attachments" class="fileUpload" :file-type='["png", "jpg", "jpeg","gif","doc", "xls", "ppt", "txt", "pdf"]'  />

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

    <el-dialog title="作业详情" :visible.sync="showDetail" width="90%" append-to-body>
      <homework-detail :homework="currentHomework"/>
    </el-dialog>

    <major-selector ref="majorSelector" @select="handleMjaorSelected"/>
    <class-selector ref="classSelector" @select="handleClassSelected"/>
    <course-selector ref="courseSelector" @select="handleCourseSelected"/>
    <paper-selector ref="paperSelector" @select="handlePaperSelected"/>




  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import {
    listClass_homework,
    getClass_homework,
    delClass_homework,
    addClass_homework,
    updateClass_homework,
    publish
  } from "@/api/teach/class_homework";

  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {isSingleNode} from '@/utils/util'

  import majorSelector from "@/views/teach/major/majorSelector";
  import classSelector from "@/views/teach/classes/classSelector";
  import courseSelector from "@/views/teach/course/courseSelector";
  import paperSelector from "@/views/exam/paperSelector";

  import homeworkDetail from "./homeworkDetail";

  export default {
    name: "Class_homework",
    dicts: ['exam_publish_status', 'sys_yes_no', 'teach_homework_types'],
    components: {Treeselect, majorSelector, classSelector, courseSelector, paperSelector,homeworkDetail},
    computed: {
      ...mapGetters([
        'teacher',
        'college'
      ]),
    },
    data() {
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
        selectedMajorName: null,
        selectedClassName: null,
        selectedCourseName: null,
        selectedPaperName: null,

        currentHomework:{},
        showDetail:false,

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
        // 班级作业表格数据
        class_homeworkList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          collegeId: null,
          courseId: null,
          classId: null,
          paperId: null,
          stageId: null,
          description: null,
          homeworkType: null,
          failCount: null,
          passCount: null,
          fineCount: null,
          goodCount: null,
          fullCount: null,
        },
        // 表单参数
        form: {
          passScore: 60,
          fineScore: 80,
          goodScore: 90,
          fullScore: 100,
          collegeId: null
        },
        // 表单校验
        rules: {
          name: [
            {required: true, message: "作业名称不能为空", trigger: "blur"}
          ],
          classId: [
            {required: true, message: "班级ID不能为空", trigger: "blur"}
          ],
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
    },
    created() {
      this.form.collegeId = this.college.deptId;
      this.getList();
      this.getTreeselect();
    },
    methods: {
      /** 查询班级作业列表 */
      getList() {
        this.loading = true;
        listClass_homework(this.queryParams).then(response => {
          this.class_homeworkList = response.rows;
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
          name: null,
          collegeId: this.college.deptId,
          teacherId: null,
          courseId: null,
          classId: null,
          paperId: null,
          stageId: null,
          description: null,
          homeworkType: null,
          studentCount: null,
          submitCount: null,
          deadLine: null,
          passScore: null,
          fineScore: null,
          goodScore: null,
          fullScore: null,
          failCount: null,
          passCount: null,
          fineCount: null,
          goodCount: null,
          fullCount: null,
          remark: null,
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
        this.title = "添加班级作业";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {

        if (row.publishStatus == 0) {
          this.$modal.msgSuccess("作业已布置，不能进行编辑");
          return;
        }

        this.reset();
        const id = row.id || this.ids
        getClass_homework(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改班级作业";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateClass_homework(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addClass_homework(this.form).then(response => {
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

        if (row.publishStatus == 0) {
          this.$modal.msgSuccess("作业已布置，不能删除");
          return;
        }

        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除班级作业编号为"' + ids + '"的数据项？').then(function () {
          return delClass_homework(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/class_homework/export', {
          ...this.queryParams
        }, `class_homework_${new Date().getTime()}.xlsx`)
      },

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

      handleCollegeSelected(node, id) {
        //this.selectedCollegeName = node.label;
        this.$refs.majorSelector.collegeName = node.label;
        this.$refs.majorSelector.queryParams.collegeId = node.id;

        this.$refs.classSelector.collegeName = node.label;
        this.$refs.classSelector.queryParams.collegeId = node.id;

        this.form.collegeName = node.label;
      },

      //专业选择
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
      },

      //班级选择
      showClassSelector() {

        if (!this.form.collegeId) {
          this.$message({
            message: '请先选择所属院校！',
            type: 'warning'
          });
          return;
        }

        this.$refs.classSelector.show();
      },
      handleClassSelected(classes) {
        this.form.classId = classes.id;
        this.form.className = classes.className;
        this.selectedClassName = classes.className;
      },

      //课程选择
      showCourseSelector() {

        if (!this.form.collegeId) {
          this.$message({
            message: '请先选择所属院校！',
            type: 'warning'
          });
          return;
        }

        this.$refs.courseSelector.collegeName = this.form.collegeName;
        this.$refs.courseSelector.queryParams.collegeId = this.form.collegeId;
        this.$refs.courseSelector.show();
      },
      handleCourseSelected(course) {
        this.form.courseId = course.id;
        this.selectedCourseName = course.courseName;
      },


      //试卷选择
      showPaperSelector() {

        if (!this.form.collegeId) {
          this.$message({
            message: '请先选择所属院校！',
            type: 'warning'
          });
          return;
        }

        this.$refs.paperSelector.collegeName = this.form.collegeName;
        this.$refs.paperSelector.queryParams.collegeId = this.form.collegeId;
        this.$refs.paperSelector.show();
      },
      handlePaperSelected(paper) {
        this.form.paperId = paper.id;
        this.selectedPaperName = paper.examinationName;
        this.form.fullScore = paper.totalScore;
      },


      handleDetail(row){
        const id = row.id || this.ids;

        getClass_homework(id).then(response => {
          this.currentHomework = response.data;
          this.showDetail = true;
        });


      },

      handlePublish(row) {

        this.$modal.confirm('是否确认要布置班级作业【' + row.name + '】？').then(
          () => {

            publish(row.id).then(response => {
              this.$modal.msgSuccess("布置成功");
              this.open = false;
              this.getList();
            })

          }
        ).catch(() => {
        });


      }
    }


  }

</script>
