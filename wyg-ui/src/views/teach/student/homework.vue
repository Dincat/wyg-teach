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

          <el-form-item label="班级" prop="params.className" v-if="classId<1">
            <el-input
              v-model="queryParams.params.className"
              placeholder="请输入班级名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>


          <el-form-item label="发布教师" prop="params.teacherName"  v-if="classId<1">
            <el-input
              v-model="queryParams.params.teacherName"
              placeholder="请输入发布教师姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="作答学生" prop="params.studentName">
            <el-input
              v-model="queryParams.params.studentName"
              placeholder="请输入作答学生姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">

          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['teach:student:homework:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="student_homeworkList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="ID" align="center" prop="id"/>
          <el-table-column label="班级" align="center" prop="classes.className"/>
          <el-table-column label="学生姓名" align="center" prop="student.stuName"/>
          <el-table-column label="分数" align="center" prop="score"/>
          <el-table-column label="作业状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.teach_homework_status" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['teach:student:homework:query']"
              >详情
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

    <!-- 添加或修改学生作业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属院校" prop="collegeId">
          <el-input v-model="form.collegeId" placeholder="请输入所属院校"/>
        </el-form-item>
        <el-form-item label="班级ID" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级ID"/>
        </el-form-item>
        <el-form-item label="阶段ID" prop="stageId">
          <el-input v-model="form.stageId" placeholder="请输入阶段ID"/>
        </el-form-item>
        <el-form-item label="所属科目" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入所属科目"/>
        </el-form-item>
        <el-form-item label="班级作业ID" prop="homeworkId">
          <el-input v-model="form.homeworkId" placeholder="请输入班级作业ID"/>
        </el-form-item>
        <el-form-item label="发布教师" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入发布教师"/>
        </el-form-item>
        <el-form-item label="作答学生" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入作答学生"/>
        </el-form-item>
        <el-form-item label="附件" prop="attachments">
          <el-input v-model="form.attachments" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="试卷作答记录ID" prop="paperRecordId">
          <el-input v-model="form.paperRecordId" placeholder="请输入试卷作答记录ID"/>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="form.answer" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入分数"/>
        </el-form-item>
        <el-form-item label="结果" prop="result">
          <el-input v-model="form.result" placeholder="请输入结果"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import {
    listStudent_homework,
    getStudent_homework,
    delStudent_homework,
    addStudent_homework,
    updateStudent_homework
  } from "@/api/teach/student_homework";

  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {isSingleNode} from '@/utils/util'

  export default {
    name: "Student_homework",
    dicts: ['teach_homework_status'],
    computed: {
      ...mapGetters([
        'teacher',
        'college'
      ]),
    },
    props: {
      classId: {
        type: Number,
        default:0
      }
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
        // 学生作业表格数据
        student_homeworkList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          collegeId: null,
          classId: null,
          stageId: null,
          courseId: null,
          homeworkId: null,
          teacherId: null,
          studentId: null,
          attachments: null,
          paperRecordId: null,
          answer: null,
          score: null,
          result: null,
          status: null,
          params:{
            className:null,
            teacherName:null,
            studentName:null
          }
        },
        // 表单参数
        form: {

        },
        // 表单校验
        rules: {}
      };
    },
    watch: {
      // 根据名称筛选院校树
      collegeName(val) {
        this.$refs.tree.filter(val);
      },

    },
    created() {
      this.form.collegeId = this.college.deptId;
      this.getList();
      this.getTreeselect();

    },
    methods: {
      /** 查询学生作业列表 */
      getList() {
        this.loading = true;
        listStudent_homework(this.queryParams).then(response => {
          this.student_homeworkList = response.rows;
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
          collegeId: null,
          classId: null,
          stageId: null,
          courseId: null,
          homeworkId: null,
          teacherId: null,
          studentId: null,
          attachments: null,
          paperRecordId: null,
          answer: null,
          score: null,
          result: null,
          status: "0",
          createTime: null,
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
        this.title = "添加学生作业";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getStudent_homework(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改学生作业";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateStudent_homework(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addStudent_homework(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除学生作业编号为"' + ids + '"的数据项？').then(function () {
          return delStudent_homework(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/student_homework/export', {
          ...this.queryParams
        }, `student_homework_${new Date().getTime()}.xlsx`)
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


    }
  };
</script>
