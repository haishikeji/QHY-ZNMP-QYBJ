<template>
<div>
  <el-dialog
  width="80%"
    :title="title"
    :modal="false"
     v-dialogDrag
    :visible.sync="visible">
    <el-form :model="inputForm" size="small" ref="inputForm" v-loading="loading" :class="method==='view'?'readonly':''"  :disabled="method==='view'"
             label-width="120px">
      <el-row  :gutter="15">
        <el-col :span="12">
            <el-form-item label="企业" prop="qy.id"
                :rules="[
                  {required: true, message:'企业不能为空', trigger:'blur'}
                 ]">
          <GridSelect
          :disabled="true"
            title="选择企业"
            labelName = 'name'
            labelValue = 'id'
            :value = "inputForm.qy.id"
            :limit="1"
            @getValue='(value) => {inputForm.qy.id=value}'
            :columns="[
            {
              prop: 'name',
              label: '企业名称'
            }
            ]"
            :searchs="[
            {
              prop: 'name',
              label: '企业名称'
            }
            ]"
            :dataListUrl="user1.roleNames.indexOf('系统管理员') != -1?'/qy/enterprise/enterprise/list':'/qy/enterprise/enterprise/selectlist'"
            queryEntityUrl="/qy/enterprise/enterprise/queryById">
          </GridSelect>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="用户" prop="user.id"
                :rules="[
                  {required: true, message:'用户不能为空', trigger:'blur'}
                 ]">
                <UserIndex :limit='1' :qyid="inputForm.qy.id" :value="inputForm.user.id" @getValue='(value) => {inputForm.user.id=value}'></UserIndex>
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
  import UserIndex from './UserIndex.vue'
  // import UserSelect from '@/components/userSelect'
  import AdminUserService from '@/api/qy/adminuser/AdminUserService'
  import RoleService from "@/api/sys/RoleService";
  import UserService from "@/api/sys/UserService";
import User from './UserIndex.vue'
  export default {
    data () {
      return {
        title: '',
        method: '',
        visible: false,
        loading: false,
        inputForm: {
          id: '',
          qy: {
            id: ''
          },
          user: {
            id: ''
          }
        },
        roleIdList:[],
        user: {
        id: '',
        companyDTO: { // 归属公司
          id: '',
          name: ''
        },
        officeDTO: {
          id: '',
          name: ''
        },
        roleIdList: [],
        postIdList: [],
        loginName: '', // 登录名
        no: '', // 工号
        name: '', // 姓名
        email: '', // 邮箱
        phone: '', // 电话
        mobile: '', // 手机
        loginFlag: '1', // 是否允许登陆
        photo: '', // 头像
        qrCode: '', // 二维码
        oldLoginName: '', // 原登录名
        newPassword: '', // 新密码
        confirmNewPassword: '',
        sign: '', // 签名
        remarks: '' // 备注

      },
      user1:{}
      }
    },
    components: {
      GridSelect,
        UserIndex
    },
    adminUserService: null,
    created () {
      this.adminUserService = new AdminUserService()
      new RoleService().list().then(({data})=>{
          this.roleIdList.push(data.records[1].id);
          this.roleIdList.push(data.records[2].id);
      });
      this.user1 = JSON.parse(window.localStorage.getItem("user"));
    },
    methods: {
      init (method, id) {
        this.method = method
        // this.inputForm.id = id
        if (method === 'add') {
          this.title = `新建企业管理员`
        } else if (method === 'edit') {
          this.title = '修改企业管理员'
        } else if (method === 'view') {
          this.title = '查看企业管理员'
        }
        this.visible = true
        this.loading = false
        this.$nextTick(() => {
          this.$refs.inputForm.resetFields()
          this.inputForm.qy.id = id
          if (method === 'edit' || method === 'view') { // 修改或者查看
            this.loading = true
            this.adminUserService.queryById(this.inputForm.id).then(({data}) => {
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
            new UserService().queryById(this.inputForm.user.id).then(({data})=>{
              this.user = data
              this.user.oldLoginName = data.loginName
              this.user.roleIdList = this.roleIdList;
              this.user.roleIds = this.roleIdList.join(',');
              new UserService().save(this.user).then((res)=>{
              })
            })
            this.adminUserService.save(this.inputForm).then(({data}) => {
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

  
