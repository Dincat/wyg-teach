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
          <el-form-item label="课程名称" prop="courseName">
            <el-input
              v-model="queryParams.courseName"
              placeholder="请输入课程名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="启用状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择启用状态" clearable>
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
              v-hasPermi="['teach:course:edit']"
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
              v-hasPermi="['teach:course:remove']"
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
              v-hasPermi="['teach:course:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>

          <el-table-column label="主键" align="center" prop="id"/>
          <el-table-column label="课程名称" align="center" prop="courseName"/>
          <el-table-column label="学院" align="center" prop="college.deptName"/>
          <el-table-column label="启用状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="评分" align="center" prop="rating"/>
          <el-table-column label="封面" align="center" prop="cover">
            <template slot-scope="scope">
              <ImagePreview :src="scope.row.cover" :width="64"/>
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
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['teach:course:remove']"
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

    <!-- 添加或修改课程信息对话框 -->
    <el-dialog :title="title" :visible.sync="open"  width="50%" top="10vh" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-row>

          <el-col :span="18">
            <el-col :span="24">
              <el-form-item label="所属院校" prop="collegeId">
                <treeselect v-model="form.collegeId" :options="deptOptions" :show-count="true"
                            placeholder="请选择所属院校"/>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="课程名称" prop="courseName">
                <el-input v-model="form.courseName" placeholder="请输入课程名称"/>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="启用状态">
                <el-radio-group v-model="form.status">
                  <el-radio
                    v-for="dict in dict.type.sys_normal_disable"
                    :key="dict.value"
                    :label="dict.value"
                  >{{dict.label}}
                  </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="课程描述" prop="courseDescription">
                <el-input v-model="form.courseDescription" placeholder="请输入课程描述"/>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="课程介绍" prop="introduce">
                <el-input v-model="form.introduce" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-col>


          <el-col :span="5" :offset="1">
            <el-row>
              <ImagePreview :src="form.cover+'.256.jpg'"/>
            </el-row>

            <el-row align="middle">
              <el-button size="small" @click="openMaterial">
                选择
                <i class="el-icon-upload el-icon--right"></i>
              </el-button>
            </el-row>

          </el-col>

        </el-row>



      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <MaterialBox :visible.sync="showMaterialBox" :max="1" :clear="true" @change="selectedMaterial"/>
  </div>
</template>

<script>
  import {listCourse, getCourse, delCourse, addCourse, updateCourse} from "@/api/teach/course";
  import {treeselect} from "@/api/system/dept";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {isSingleNode} from '@/utils/util'

  export default {
    name: "Course",
    dicts: ['sys_normal_disable'],
    components: { Treeselect },
    data() {
      return {
        // 遮罩层
        loading: true,
        showMaterialBox:false,

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
        // 课程信息表格数据
        courseList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          courseName: null,
          collegeId: null,
          collegeName: null,
          majorId: null,
          majorName: null,
          teacherId: null,
          courseDescription: null,
          cover: null,
          status: null,
          orderNum: null,
          rating: null,
          buyCount: null,
          period: null,
          showIndex: null,
          isShow: null,
          introduce: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          courseName: [
            {required: true, message: "课程名称不能为空", trigger: "blur"}
          ],
        },




      };
    },
    watch: {
      // 根据名称筛选院校树
      collegeName(val) {
        this.$refs.tree.filter(val);
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


      //打开文件管理
      openMaterial(){
        this.showMaterialBox=true;
      },
      selectedMaterial(materialList){
        if(materialList==null || materialList.length<1) return;
        var image=materialList[materialList.length-1];
        this.form.cover=image.materialUrl;
      },


      /** 查询课程信息列表 */
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
          courseName: null,
          collegeId: null,
          collegeName: null,
          majorId: null,
          majorName: null,
          teacherId: null,
          courseDescription: null,
          cover: null,
          status: "0",
          orderNum: null,
          hits: null,
          rating: null,
          buyCount: null,
          period: null,
          showIndex: null,
          isShow: null,
          introduce: null,
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
        this.title = "添加课程信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getCourse(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改课程信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
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
        this.$modal.confirm('是否确认删除课程信息编号为"' + ids + '"的数据项？').then(function () {
          return delCourse(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/course/export', {
          ...this.queryParams
        }, `course_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
