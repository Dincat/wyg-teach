<template>
  <div class="app-container">
    <!-- 授权用户 -->
    <el-dialog title="选择班级" :visible.sync="visible" width="70%" top="5vh" append-to-body>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="所属院校" prop="collegeId">
          <el-input disabled
                    v-model="collegeName"
          />
        </el-form-item>

        <el-form-item label="试卷名称" prop="examinationName">
          <el-input clearable size="small" placeholder="请输入试卷名称" v-model="queryParams.examinationName" style="width: 200px;" class="filter-item"
                    @keyup.enter.native="handleFilter"/>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>


      <el-table ref="table" v-loading="loading" :data="classesList" @selection-change="handleSelectionChange"  @select="rowSelected">
        <el-table-column type="selection" width="55"/>
        <el-table-column :label="$t('table.examinationName')" min-width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.examinationName }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.examinationType')">
          <template slot-scope="scope">
            <!--          <span>{{ scope.row.type | examTypeFilter }}</span>-->
            <dict-tag :options="dict.type.examination_type" :value="scope.row.type"/>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.course')">
          <template slot-scope="scope">
            <span>{{ scope.row | courseFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.totalScore')" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.totalScore }}</span>
          </template>
        </el-table-column>
        <el-table-column label="作答时长（分钟）" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.duration }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.status')" align="center">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.exam_publish_status" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.modifier')">
          <template slot-scope="scope">
            <span>{{ scope.row.modifier }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.modifyDate')">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.modifyDate) }}</span>
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

      <div slot="footer" class="dialog-footer">
        <el-button @click="handleSelect()">确 定</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<script>
  import { fetchList } from '@/api/exam/exam'
  import { checkMultipleSelect, isNotEmpty, notifySuccess, notifyFail, messageSuccess } from '@/utils/util'

  export default {
    name: "TeacherSelector",
    dicts: ['teach_edu_system','examination_type','exam_publish_status','sys_yes_no','exam_publish_status'],
    filters: {
      courseFilter (row) {
        if (isNotEmpty(row.course) && isNotEmpty(row.course.courseName)) {
          return row.course.courseName
        }
        return ''
      }
    },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 遮罩层
        visible: false,
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
          status:'0',
          collegeId: null,
        },
        // 表单参数
        form: {},
        collegeName:'',
        selectedItem: null
      };
    },

    methods: {

      /** 查询专业信息列表 */
      getList() {
        this.loading = true;
        fetchList(this.queryParams).then(response => {
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
          majorName: null,
          majorType: null,
          collegeId: null,
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

      rowSelected(selection, row){
        this.$refs.table.clearSelection()
        if(selection.length === 0){  //判断election是否有值存在
          return
        }
        if(row){
          this.selectedItem = row
          this.$refs.table.toggleRowSelection(row, true)
        }
      },

      // 显示弹框
      show() {
        this.getList();
        this.visible = true;
      },
      handleSelect() {
        this.$emit("select", this.selectedItem);
        this.visible = false;
      }
    }
  };
</script>
