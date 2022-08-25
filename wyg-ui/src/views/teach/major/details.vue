<template>
  <div class="app-container">
    <el-row :gutter="40">
      <el-col :span="24">
        <el-card class="title-info">
          <div slot="header">
            <span>专业详情</span>
          </div>

          <el-form ref="stageForm" :model="form" label-width="100px" size="mini">
            <el-row>
              <el-col :span="12">
                <el-form-item label="专业名称：">{{ majorInfo.majorName }}</el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="专业类型：">
                  <dict-tag :options="dict.type.teach_edu_system" :value="majorInfo.majorType"/>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="所属院校：">{{ majorInfo.college.deptName }}</el-form-item>
              </el-col>


              <el-col :span="12">
                <el-form-item label="创建时间：">{{ majorInfo.createTime }}</el-form-item>
              </el-col>



            </el-row>
          </el-form>

        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="40">
      <el-col :span="24">
        <el-card class="course-list">
          <div slot="header">
            <span>课程信息</span>
          </div>

          <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

            <el-form-item label="课程名称" prop="courseId">
              <el-input
                v-model="queryParams.courseName"
                placeholder="请输入课程课程名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>

            <el-form-item label="所属阶段" prop="status">
              <el-select v-model="queryParams.stageId" placeholder="请选择所属阶段" clearable>
                <el-option
                  v-for="stage in stageList"
                  :key="stage.id"
                  :label="stage.stageName"
                  :value="stage.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="课程状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择课程状态" clearable>
                <el-option
                  v-for="dict in dict.type.sys_normal_disable"
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
                v-hasPermi="['teach:course:add']"
              >新增</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="success"
                plain
                icon="el-icon-edit"
                size="mini"
                :disabled="single"
                @click="handleUpdate"
                v-hasPermi="['teach:course:edit']"
              >修改</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="danger"
                plain
                icon="el-icon-delete"
                size="mini"
                :disabled="multiple"
                @click="handleDelete"
                v-hasPermi="['teach:course:remove']"
              >删除</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="warning"
                plain
                icon="el-icon-download"
                size="mini"
                @click="handleExport"
                v-hasPermi="['teach:course:export']"
              >导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
          </el-row>

          <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="ID" align="center" prop="id" />
            <el-table-column label="课程名称" align="center" prop="course.courseName" />
            <el-table-column label="所属阶段" align="center" prop="stageName" />
            <el-table-column label="课时数" align="center" prop="period" />
            <el-table-column label="课程状态" align="center" prop="status">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
              </template>
            </el-table-column>
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建时间" align="center" prop="createTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="更新人" align="center" prop="updateBy"/>
            <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
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
                  v-hasPermi="['teach:course:edit']"
                >修改</el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['teach:course:remove']"
                >删除</el-button>
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


        </el-card>
      </el-col>
    </el-row>


    <!-- 添加或修改专业课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程" prop="courseId">
          <el-input v-model="selectedCourseName" placeholder="请选择课程"  @click.native="showCourseSelector"/>
        </el-form-item>
        <el-form-item label="所属阶段" prop="stage">
          <el-select v-model="form.stageId" placeholder="请选择所属阶段"   clearable>
            <el-option
              v-for="stage in stageList"
              :key="stage.id"
              :label="stage.stageName"
              :value="stage.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="课时数" prop="period">
          <el-input-number v-model="form.period" controls-position="right" :min="0" placeholder="请输入课时数" />
        </el-form-item>

        <el-form-item label="课程状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <course-selector ref="courseSelector" @select="handleCourseSelected"/>
  </div>
</template>

<script>
  import {listStage} from "@/api/teach/stage";
  import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/teach/majorCourse";
  import courseSelector from "@/views/teach/course/courseSelector";

  export default {
    name: "MajorDetails",
    dicts: ['teach_edu_system','sys_normal_disable'],
    components: { courseSelector },
    props: {
      majorInfo: {
        type: Object,
        default:
          {
            college: {
              collegeName: null,
            }
          }
      }
    },

    data(){
      return{
        // 遮罩层
        loading: true,

        // 是否显示弹出层
        open: false,

        // 非单个禁用
        single: true,

        // 非多个禁用
        multiple: true,
        title:null,
        selectedCourseName:null,
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

        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 专业课程表格数据
        courseList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          collegeId: null,
          majorId: null,
          courseId: null,
          period: null,
          status: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          collegeId: [
            {required: true, message: "院校不能为空", trigger: "blur"}
          ],
          majorId: [
            {required: true, message: "专业不能为空", trigger: "blur"}
          ],
          stageId: [
            {required: true, message: "专业不能为空", trigger: "blur"}
          ],
          // courseId: [
          //   {required: true, message: "课程ID不能为空", trigger: "blur"}
          // ],
        }



      }
    },
    watch: {
      majorInfo: {
        immediate:true,
        handler(newValue, oldValue) {
          if(!this.majorInfo) return;
          this.getStageList();
          this.getList();
        },
        deep: true
      },

    },
    methods:{
      /** 查询学期阶段列表 */
      getStageList() {
        this.stageList = [];

        this.stageQueryParams.collegeId = this.majorInfo.collegeId;
        this.stageQueryParams.eduSystem = this.majorInfo.majorType;

        listStage(this.stageQueryParams).then(response => {
          this.stageList = response.rows;
        });
      },

      /** 查询专业课程列表 */
      getList() {
        this.loading = true;
        listCourse(this.queryParams).then(response => {
          this.courseList = response.rows;
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
          majorId: null,
          courseId: null,
          period: null,
          status: "0",
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
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加专业课程";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getCourse(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改专业课程";
        });
      },
      /** 提交按钮 */
      submitForm() {

        this.form.collegeId = this.majorInfo.collegeId;
        this.form.majorId = this.majorInfo.id;

        for(var i=0;i<this.stageList.length;i++){
          var stage=this.stageList[i];

          if(stage.id===this.form.stageId){
            this.form.stageName=stage.stageName;
          }

        }

        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateCourse(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCourse(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除专业课程编号为"' + ids + '"的数据项？').then(function() {
          return delCourse(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/course/export', {
          ...this.queryParams
        }, `course_${new Date().getTime()}.xlsx`)
      },



      showCourseSelector() {

        if (!this.majorInfo.collegeId) {
          this.$message({
            message: '请先选择所属院校！',
            type: 'warning'
          });
          return;
        }

        this.$refs.courseSelector.collegeName =  this.majorInfo.college.deptName;
        this.$refs.courseSelector.queryParams.collegeId =this.majorInfo.collegeId;
        this.$refs.courseSelector.show();
      },
      handleCourseSelected(course) {
        this.form.courseId = course.id;
        this.selectedCourseName = course.courseName;
      }




    }

  }
</script>

<style lang="scss" scoped>
  .details-container {
    padding: 12px;

  }

  .course-list {
    margin-top: 12px;
  }
</style>


