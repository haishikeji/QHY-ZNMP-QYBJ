<template>
  <div>
    <el-dialog :title="title" :close-on-click-modal="false" v-dialogDrag :visible.sync="visible" width="70%">
      <el-form :model="inputForm" size="small" ref="inputForm" label-position="right" v-loading="loading"
        :class="method === 'view' ? 'readonly' : ''" :disabled="method === 'view'" label-width="150px">
        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item label="企业名称" prop="qy.id" :rules="[
              { required: true, message: '企业id不能为空', trigger: 'blur' },
            ]">
              <GridSelect ref="select" title="选择企业" labelName="name" labelValue="id" :value="inputForm.qy.id" :limit="1" @getValue="(value,name) => {
                  inputForm.qy.id = value;inputForm.firmName = name;this.getdata(value);
                }
                " :columns="[
                  {
                    prop: 'name',
                    label: '企业名称',
                  },
                ]" :searchs="[
                {
                  prop: 'name',
                  label: '企业名称',
                },
              ]" dataListUrl="/qy/enterprise/enterprise/qyselect" queryEntityUrl="/qy/enterprise/enterprise/queryById">
              </GridSelect>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="单位名称" prop="firmName" :rules="[]">
              <el-input v-model="inputForm.firmName" placeholder="请填写单位名称"></el-input>
            </el-form-item>
          </el-col> -->

          <el-col :span="12">
            <el-form-item label="单位证件类型" prop="firmCardType">
              <el-select v-model="inputForm.firmCardType" placeholder="请选择" style="width: 100%">
                <el-option v-for="item in $dictUtils.getDictList('firm_document_type')" :key="item.value"
                  :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="单位证件号" prop="firmCardID"
                :rules="[
                  {required: true, message:'单位证件号不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.firmCardID" placeholder="请填写单位证件号"     ></el-input>
           </el-form-item>
        </el-col> -->
          <!-- <el-col :span="12">
            <el-form-item label="单位性质" prop="firmNature"
                :rules="[
                  {required: true, message:'单位性质不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.firmNature" placeholder="请填写单位性质"     ></el-input>
           </el-form-item>
        </el-col> -->
          <el-col :span="12">
            <el-form-item label="行业类型" prop="firmIndustryType.id" :rules="[]">
              <SelectTree ref="firmIndustryType" :props="{
                value: 'id', // ID字段名
                label: 'name', // 显示名称
                children: 'children', // 子级字段名
              }" url="/fw/industry_types/industryTypes/treeData" :value="inputForm.firmIndustryType.id"
                :clearable="true" :accordion="true" @getValue="(value) => {
                    inputForm.firmIndustryType.id = value;
                  }
                  " />
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="注册机关" prop="firmRegister"
                :rules="[
                 ]">
              <el-input v-model="inputForm.firmRegister" placeholder="请填写注册机关"     ></el-input>
           </el-form-item>
        </el-col> -->
          <el-col :span="12">
            <el-form-item label="成立时间" prop="firmStareDate" :rules="[]">
              <el-date-picker style="width: 100%" v-model="inputForm.firmStareDate" type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="官方网站" prop="firmWebsite"
                :rules="[
                 ]">
              <el-input v-model="inputForm.firmWebsite" placeholder="请填写官方网站"     ></el-input>
           </el-form-item>
        </el-col> -->
          <el-col :span="24">
            <el-form-item label="营业执照照片" prop="firmCardImg" :rules="[
              {
                required: true,
                message: '单位证件照片不能为空',
                trigger: 'blur',
              },
            ]">
              <el-upload ref="firmCardImg" v-if="visible" list-type="picture-card"
                :action="`${this.$http.BASE_URL}/sys/file/webupload/upload?uploadPath=/qy/firmcertification/firmCertification`"
                :headers="{ token: $cookie.get('token') }" :on-preview="(file, fileList) => {
                    $alert(
                      `<img style='width:100%' src=' ${(file.response && file.response.url) || file.url
                      }'/>`,
                      {
                        dangerouslyUseHTMLString: true,
                        showConfirmButton: false,
                        closeOnClickModal: true,
                        customClass: 'showPic',
                      }
                    );
                  }
                  " :on-success="(response, file, fileList) => {
      inputForm.firmCardImg = fileList
        .map(
          (item) =>
            (item.response && item.response.url) || item.url
        )
        .join('|');
    }
    " :on-remove="(file, fileList) => {
      $http
        .delete(
          `/sys/file/webupload/deleteByUrl?url=${(file.response && file.response.url) || file.url
          }`
        )
        .then(({ data }) => {
          $message.success(data);
        });
      inputForm.firmCardImg = fileList
        .map((item) => item.url)
        .join('|');
    }
    " :before-remove="(file, fileList) => {
      return $confirm(`确定移除 ${file.name}？`);
    }
    " multiple :limit="5" :on-exceed="(files, fileList) => {
      $message.warning(
        `当前限制选择 5 个文件，本次选择了 ${files.length
        } 个文件，共选择了 ${files.length + fileList.length
        } 个文件`
      );
    }
    " :file-list="firmCardImgArra">
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="单位前缀" prop="firmPrefix"
                :rules="[
                  {required: true, message:'单位前缀不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.firmPrefix" placeholder="请填写单位前缀"     ></el-input>
           </el-form-item>
        </el-col> -->
          <el-col :span="12">
            <el-form-item label="单位地区"  prop="firmRegion" :rules="[
              {
                required: true,
                message: '单位地区不能为空',
                trigger: 'blur',
              },
            ]">
              <CityPicker style="width: 100%" disabled :value="inputForm.firmRegion" :clearable="true" :accordion="true" @getValue="(value) => {
                  inputForm.firmRegion = value;
                }
                " />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="详细地址"  prop="firmAddress" :rules="[
              {
                required: true,
                message: '详细地址不能为空',
                trigger: 'blur',
              },
            ]">
              <el-input disabled v-model="inputForm.firmAddress" placeholder="请填写详细地址"></el-input>
            </el-form-item>
          </el-col>


          <!-- <el-col :span="12">
            <el-form-item label="有效日期" prop="firmEndDate" :rules="[]">
              <el-date-picker style="width: 100%" v-model="inputForm.firmEndDate" type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
          </el-col> -->



          <el-col :span="24">
           <el-form-item label-width="0">
                  <el-tabs v-model="allTab">
                  <el-tab-pane label="资质证书">
                        <el-button size="small" @click="addMessageManageRow" type="primary">添加</el-button>
                        <el-table
                        class="table"
                        size="small"
                        :data="inputForm.certificationDetilDTOList.filter(function(item){ return item.delFlag !== '1'})"
                        style="width: 100%">
                            <el-table-column
                              prop="name"
                              header-align="center"
                              align="center"
                              show-overflow-tooltip
                              label="名称">
                            </el-table-column>
                            <el-table-column
                              prop="stime"
                              header-align="center"
                              align="center"
                              show-overflow-tooltip
                              label="起止时间">


                              <template slot-scope="scope">
                                 {{scope.row.stime.slice(0,10)}} 至 {{scope.row.etime.slice(0,10)}}
                              </template>

                            </el-table-column>
                            <el-table-column
                                prop="simg"
                                header-align="center"
                                align="center"
                                show-overflow-tooltip
                                label="证书扫描件">

                                <template slot-scope="scope">
                                   <div ststyle="display: flex"  >
                                      <img :src="item" v-for="item in scope.row.simgarr" />
                                   </div>

                                </template>


                            </el-table-column>
                          <el-table-column
                            fixed="right"
                            label="操作"
                            width="150">
                            <template slot-scope="scope">
                              <el-button @click="viewMessageManageRow(scope.row)" type="text" size="small">查看</el-button>
                              <el-button  @click="editMessageManageRow(scope.row)" type="text" size="small">编辑</el-button>
                              <el-button  @click="delMessageManageRow(scope.row)" type="text" size="small">删除</el-button>
                            </template>
                          </el-table-column>
                      </el-table>
                    </el-tab-pane>
                  </el-tabs>
                 </el-form-item>
                </el-col>




          <!-- <el-col :span="12">
            <el-form-item label="单位简介" prop="firmIntroduce" :rules="[]">
              <el-input v-model="inputForm.firmIntroduce" placeholder="请填写单位简介"></el-input>
            </el-form-item>
          </el-col> -->
          <!-- <el-col :span="12">
            <el-form-item label="法定代表人姓名" prop="legalName"
                :rules="[
                  {required: true, message:'法定代表人姓名不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.legalName" placeholder="请填写法定代表人姓名"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="法定代表人证件类型" prop="legalCardType"
                :rules="[
                  {required: true, message:'法定代表人证件类型不能为空', trigger:'blur'}
                 ]">
                <el-select v-model="inputForm.legalCardType" placeholder="请选择"  style="width: 100%;">
                          <el-option
                            v-for="item in $dictUtils.getDictList('sys_cert_type')"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                      </el-select>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="法定代表人证件号" prop="legalCardnumber"
                :rules="[
                  {required: true, message:'法定代表人证件号不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.legalCardnumber" placeholder="请填写法定代表人证件号"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="法定代表人手机号" prop="legalPhone"
                :rules="[
                  {required: true, message:'法定代表人手机号不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.legalPhone" placeholder="请填写法定代表人手机号"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="法定代表人传真" prop="legalFax"
                :rules="[
                 ]">
              <el-input v-model="inputForm.legalFax" placeholder="请填写法定代表人传真"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="法定代表人邮箱" prop="legalEmail"
                :rules="[
                 ]">
              <el-input v-model="inputForm.legalEmail" placeholder="请填写法定代表人邮箱"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="24">
            <el-form-item label="法定代表人证件照片" prop="legalCardImg"
                :rules="[
                  {required: true, message:'法定代表人证件照片不能为空', trigger:'blur'}
                 ]">
              <el-upload ref="legalCardImg"
              v-if="visible"
              list-type="picture-card"
                    :action="`${this.$http.BASE_URL}/sys/file/webupload/upload?uploadPath=/qy/firmcertification/firmCertification`"
                    :headers="{token: $cookie.get('token')}"
                    :on-preview="(file, fileList) => {
                        $alert(`<img style='width:100%' src=' ${(file.response && file.response.url) || file.url}'/>`,  {
                          dangerouslyUseHTMLString: true,
                          showConfirmButton: false,
                          closeOnClickModal: true,
                          customClass: 'showPic'
                        });
                    }"
                    :on-success="(response, file, fileList) => {
                       inputForm.legalCardImg = fileList.map(item => (item.response && item.response.url) || item.url).join('|')
                    }"
                    :on-remove="(file, fileList) => {
                      $http.delete(`/sys/file/webupload/deleteByUrl?url=${(file.response && file.response.url) || file.url}`).then(({data}) => {
                        $message.success(data)
                      })
                      inputForm.legalCardImg = fileList.map(item => item.url).join('|')
                    }"
                    :before-remove="(file, fileList) => {
                      return $confirm(`确定移除 ${file.name}？`)
                    }"
                    multiple
                    :limit="5"
                    :on-exceed="(files, fileList) =>{
                      $message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
                    }"
                    :file-list="legalCardImgArra">
                    <i class="el-icon-plus"></i>
                  </el-upload>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="联系人姓名" prop="contactsName"
                :rules="[
                  {required: true, message:'联系人姓名不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.contactsName" placeholder="请填写联系人姓名"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="联系人证件类型" prop="contactsCardType"
                :rules="[
                  {required: true, message:'联系人证件类型不能为空', trigger:'blur'}
                 ]">
                <el-select v-model="inputForm.contactsCardType" placeholder="请选择"  style="width: 100%;">
                          <el-option
                            v-for="item in $dictUtils.getDictList('sys_cert_type')"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                      </el-select>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="联系人证件号" prop="contactsCardnumber"
                :rules="[
                  {required: true, message:'联系人证件号不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.contactsCardnumber" placeholder="请填写联系人证件号"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="联系人手机号" prop="contactsPhone"
                :rules="[
                  {required: true, message:'联系人手机号不能为空', trigger:'blur'},
                  {validator: validator.isMobile, trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.contactsPhone" placeholder="请填写联系人手机号"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="联系人邮箱" prop="contactsEmail"
                :rules="[
                  {required: true, message:'联系人邮箱不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.contactsEmail" placeholder="请填写联系人邮箱"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="24">
            <el-form-item label="联系人证件照片" prop="contactsCardImg"
                :rules="[
                  {required: true, message:'联系人证件照片不能为空', trigger:'blur'}
                 ]">
              <el-upload ref="contactsCardImg"
              v-if="visible"
              list-type="picture-card"
                    :action="`${this.$http.BASE_URL}/sys/file/webupload/upload?uploadPath=/qy/firmcertification/firmCertification`"
                    :headers="{token: $cookie.get('token')}"
                    :on-preview="(file, fileList) => {
                        $alert(`<img style='width:100%' src=' ${(file.response && file.response.url) || file.url}'/>`,  {
                          dangerouslyUseHTMLString: true,
                          showConfirmButton: false,
                          closeOnClickModal: true,
                          customClass: 'showPic'
                        });
                    }"
                    :on-success="(response, file, fileList) => {
                       inputForm.contactsCardImg = fileList.map(item => (item.response && item.response.url) || item.url).join('|')
                    }"
                    :on-remove="(file, fileList) => {
                      $http.delete(`/sys/file/webupload/deleteByUrl?url=${(file.response && file.response.url) || file.url}`).then(({data}) => {
                        $message.success(data)
                      })
                      inputForm.contactsCardImg = fileList.map(item => item.url).join('|')
                    }"
                    :before-remove="(file, fileList) => {
                      return $confirm(`确定移除 ${file.name}？`)
                    }"
                    multiple
                    :limit="5"
                    :on-exceed="(files, fileList) =>{
                      $message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
                    }"
                    :file-list="contactsCardImgArra">
                    <i class="el-icon-plus"></i>
                  </el-upload>
           </el-form-item>
        </el-col> -->
          <!-- <el-col :span="12">
            <el-form-item label="审核状态" prop="auditState" :rules="[
              { required: true, message: '法定代表人证件号不能为空', trigger: 'blur' }
            ]">
              <el-radio-group v-model="inputForm.auditState">
                <el-radio v-for="item in $dictUtils.getDictList('yes_no')" :label="item.value" :key="item.value">{{
                  item.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col> -->









<!-- 授权函注销 -->
          <!-- <el-col :span="24">
            <el-form-item label="授权函" prop="authorizationImg" :rules="[]">
              <el-upload ref="authorizationImg" v-if="visible" list-type="picture-card"
                :action="`${this.$http.BASE_URL}/sys/file/webupload/upload?uploadPath=/qy/firmcertification/firmCertification`"
                :headers="{ token: $cookie.get('token') }" :on-preview="(file, fileList) => {
                    $alert(
                      `<img style='width:100%' src=' ${(file.response && file.response.url) || file.url
                      }'/>`,
                      {
                        dangerouslyUseHTMLString: true,
                        showConfirmButton: false,
                        closeOnClickModal: true,
                        customClass: 'showPic',
                      }
                    );
                  }
                  " :on-success="(response, file, fileList) => {
      inputForm.authorizationImg = fileList
        .map(
          (item) =>
            (item.response && item.response.url) || item.url
        )
        .join('|');
    }
    " :on-remove="(file, fileList) => {
      $http
        .delete(
          `/sys/file/webupload/deleteByUrl?url=${(file.response && file.response.url) || file.url
          }`
        )
        .then(({ data }) => {
          $message.success(data);
        });
      inputForm.authorizationImg = fileList
        .map((item) => item.url)
        .join('|');
    }
    " :before-remove="(file, fileList) => {
      return $confirm(`确定移除 ${file.name}？`);
    }
    " multiple :limit="5" :on-exceed="(files, fileList) => {
      $message.warning(
        `当前限制选择 5 个文件，本次选择了 ${files.length
        } 个文件，共选择了 ${files.length + fileList.length
        } 个文件`
      );
    }
    " :file-list="authorizationImgArra">
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
          </el-col> -->
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="visible = false">关闭</el-button>
        <el-button size="small" type="primary" v-if="method != 'view'" @click="doSubmit()" v-noMoreClick>确定</el-button>
      </span>
    </el-dialog>

    <CertificationForm ref="certificationForm" @addRow="saveMessageManageRow(arguments)"></CertificationForm>
  </div>
</template>

<script>

import CertificationForm from './CertificationForm'
import GridSelect from "@/components/gridSelect";
import SelectTree from "@/components/treeSelect/treeSelect.vue";
import CityPicker from "@/components/cityPicker";
import EnterpriseService from "@/api/qy/enterprise/EnterpriseService";
import FirmCertificationService from "@/api/qy/firmcertification/FirmCertificationService";
export default {
  data() {
    return {
      title: "",
      method: "",
      allTab:"0",
      visible: false,
      loading: false,
      firmCardImgArra: [],
      legalCardImgArra: [],
      contactsCardImgArra: [],
      authorizationImgArra: [],
      user:{},
      inputForm: {
        id: "",
        certificationDetilDTOList: [],
        qy: {
          id: "",
        },
        firmName: "",
        firmCardType: "",
        firmCardID: "",
        firmNature: "",
        firmIndustryType: {
          id: "",
        },
        firmRegister: "",
        firmStareDate: "",
        firmWebsite: "",
        firmCardImg: "",
        firmPrefix: "",
        firmRegion: "",
        firmAddress: "",
        firmEndDate: "",
        firmIntroduce: "",
        auditState: 1,
        authorizationImg: "",
      },
    };
  },
  components: {
    GridSelect,
    SelectTree,
    CityPicker,
    CertificationForm
  },
  firmCertificationService: null,
  EnterpriseService:null,
  created() {
    this.firmCertificationService = new FirmCertificationService();
    this.EnterpriseService = new EnterpriseService();
    this.user = JSON.parse(window.localStorage.getItem("user"));
    //清空地区字段和详细地址字段
    this.inputForm.firmAddress = '';
    this.inputForm.firmRegion = '';
  },
  methods: {
    init(method, id, type) {
      this.method = method;
      if (type) {
        this.inputForm.qy.id = id;
      } else {
        this.inputForm.id = id;
      }

      this.inputForm.certificationDetilDTOList = []
      if (method === "add") {
        this.title = `新建企业资质认证`;
        this.EnterpriseService.queryById(this.inputForm.qy.id).then(({data})=>{
          this.inputForm.firmName = data.name
          this.inputForm.firmRegion = data.address
          this.inputForm.firmAddress = data.dzDateil
        })
      } else if (method === "edit") {
        this.title = "修改企业资质认证";
      } else if (method === "view") {
        this.title = "查看企业资质认证";
      }
      this.firmCardImgArra = [];
      this.legalCardImgArra = [];
      this.contactsCardImgArra = [];
      this.authorizationImgArra = [];
      this.visible = true;
      this.loading = false;
      this.$nextTick(() => {
        this.$refs.inputForm.resetFields();
        if (method === "edit" || method === "view") {
          // 修改或者查看
          this.loading = true;
          this.firmCertificationService
            .queryById(this.inputForm.id)
            .then(({ data }) => {
              this.inputForm = this.recover(this.inputForm, data);
              this.inputForm.firmCardImg.split("|").forEach((item) => {
                if (item.trim().length > 0) {
                  this.firmCardImgArra.push({
                    name: decodeURIComponent(
                      item.substring(item.lastIndexOf("/") + 1)
                    ),
                    url: item,
                  });
                }
              });
              if (this.inputForm.legalCardImg) {
                this.inputForm.legalCardImg.split("|").forEach((item) => {
                  if (item.trim().length > 0) {
                    this.legalCardImgArra.push({
                      name: decodeURIComponent(
                        item.substring(item.lastIndexOf("/") + 1)
                      ),
                      url: item,
                    });
                  }
                });
              }
              if (this.inputForm.contactsCardImg) {
                this.inputForm.contactsCardImg.split("|").forEach((item) => {
                  if (item.trim().length > 0) {
                    this.contactsCardImgArra.push({
                      name: decodeURIComponent(
                        item.substring(item.lastIndexOf("/") + 1)
                      ),
                      url: item,
                    });
                  }
                });
              }

              if (this.inputForm.authorizationImg) {
                this.inputForm.authorizationImg.split("|").forEach((item) => {
                  if (item.trim().length > 0) {
                    this.authorizationImgArra.push({
                      name: decodeURIComponent(
                        item.substring(item.lastIndexOf("/") + 1)
                      ),
                      url: item,
                    });
                  }
                });
              }

              //添加资质证书的图片显示
              if(this.inputForm.certificationDetilDTOList!=null&&this.inputForm.certificationDetilDTOList.length>0){
                this.inputForm.certificationDetilDTOList.forEach((item2) => {
                  if(item2.simg!=null){
                    item2.simgarr=[]
                    item2.simg.split("|").forEach((item) => {
                        if (item.trim().length > 0) {
                          item2.simgarr.push(item);
                        }
                      });
                  }



                });

              }


              this.loading = false;
            });
        }
      });
    },

    //资质证书列表操作
    saveMessageManageRow (child) {
      if (child[0] === '') {
        this.inputForm.certificationDetilDTOList.push(child[1])
      } else {
        this.inputForm.certificationDetilDTOList.forEach((item, index) => {
          if (item === child[0]) {
            this.inputForm.certificationDetilDTOList.splice(index, 1, child[1])
          }
        })
      }
    },
    addMessageManageRow (child) {
      this.$refs.certificationForm.init('add')
    },
    viewMessageManageRow (child) {
      this.$refs.certificationForm.init('view', child)
    },
    editMessageManageRow (child) {
      this.$refs.certificationForm.init('edit', child)
    },
    delMessageManageRow (child) {
      this.inputForm.certificationDetilDTOList.forEach((item, index) => {
        if (item === child && item.id === '') {
          this.inputForm.certificationDetilDTOList.splice(index, 1)
        } else if (item === child) {
          item.delFlag = '1'
          this.inputForm.certificationDetilDTOList.splice(index, 1, item)
        }
      })
    },

    //获取企业信息
    getdata(id){
      this.EnterpriseService.queryById(id).then(({data})=>{
          this.inputForm.firmName = data.name
          this.inputForm.firmRegion = data.address
          this.inputForm.firmAddress = data.dzDateil
        })
    },
    // 表单提交
    doSubmit() {
      this.$refs["inputForm"].validate((valid) => {
        if (valid) {
          this.loading = true;
          this.firmCertificationService
            .save(this.inputForm)
            .then(({ data }) => {
              this.visible = false;
              this.$message.success(data);
              this.firmCertificationService
                .updata(this.inputForm.qy.id,'1')
                .then((data) => {
                  console.log(data);
                });
              this.$emit("refreshDataList");
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        }
      });
    },
  },
};
</script>
