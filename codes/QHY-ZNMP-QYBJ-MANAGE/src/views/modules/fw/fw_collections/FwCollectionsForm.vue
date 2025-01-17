<template>
<div>
  <el-dialog
    :title="title"
    :close-on-click-modal="false"
     v-dialogDrag
    :visible.sync="visible">
    <el-form :model="inputForm" size="small" ref="inputForm" v-loading="loading" :class="method==='view'?'readonly':''"  :disabled="method==='view'"
             label-width="120px">
      <el-row  :gutter="15">
        <el-col :span="12">
            <el-form-item label="服务收藏" prop="employCollection.id"
                :rules="[
                  {required: true, message:'服务收藏不能为空', trigger:'blur'}
                 ]">
          <GridSelect
            title="选择服务收藏"
            labelName = 'title'
            labelValue = 'id'
            :value = "inputForm.employCollection.id"
            :limit="1"
            @getValue='(value) => {inputForm.employCollection.id=value}'
            :columns="[
            {
              prop: 'title',
              label: '标题'
            },
            {
              prop: 'description',
              label: '简介'
            }
            ]"
            :searchs="[
            {
              prop: 'type',
              label: '服务类型'
            },
            {
              prop: 'title',
              label: '标题'
            }
            ]"
            dataListUrl="/fw/all_employ/allEmploy/list"
            queryEntityUrl="/fw/all_employ/allEmploy/queryById">
          </GridSelect>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="用户" prop="user.id"
                :rules="[
                  {required: true, message:'用户不能为空', trigger:'blur'}
                 ]">
                <user-select :limit='1' :value="inputForm.user.id" @getValue='(value) => {inputForm.user.id=value}'></user-select>
           </el-form-item>
        </el-col>
        </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="small" @click="visible = false">关闭</el-button>
      <el-button size="small" type="primary" v-if="method != 'view'" @click="doSubmit()" v-noMoreClick>确定</el-button>
    </span>
  </el-dialog>
</div>
</template>

<script>
  import GridSelect from '@/components/gridSelect'
  import UserSelect from '@/components/userSelect'
  import FwCollectionsService from '@/api/fw/fw_collections/FwCollectionsService'
  export default {
    data () {
      return {
        title: '',
        method: '',
        visible: false,
        loading: false,
        inputForm: {
          id: '',
          employCollection: {
            id: ''
          },
          user: {
            id: ''
          }
        }
      }
    },
    components: {
      GridSelect,
      UserSelect
    },
    fwCollectionsService: null,
    created () {
      this.fwCollectionsService = new FwCollectionsService()
    },
    methods: {
      init (method, id) {
        this.method = method
        this.inputForm.id = id
        if (method === 'add') {
          this.title = `新建收藏列表`
        } else if (method === 'edit') {
          this.title = '修改收藏列表'
        } else if (method === 'view') {
          this.title = '查看收藏列表'
        }
        this.visible = true
        this.loading = false
        this.$nextTick(() => {
          this.$refs.inputForm.resetFields()
          if (method === 'edit' || method === 'view') { // 修改或者查看
            this.loading = true
            this.fwCollectionsService.queryById(this.inputForm.id).then(({data}) => {
              this.inputForm = this.recover(this.inputForm, data)
              this.loading = false
            })
          }
        })
      },
      // 表单提交
      doSubmit () {
        this.$refs['inputForm'].validate((valid) => {
          if (valid) {
            this.loading = true
            this.fwCollectionsService.save(this.inputForm).then(({data}) => {
              this.visible = false
              this.$message.success(data)
              this.$emit('refreshDataList')
              this.loading = false
            }).catch(() => {
              this.loading = false
            })
          }
        })
      }
    }
  }
</script>

  
