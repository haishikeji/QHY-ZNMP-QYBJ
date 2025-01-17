<template>
	<view>
		<cu-custom bgColor="bg-white" :isBack="true">
			<block slot="backText">
				<image src="/static/img/Home1.png" alt="" mode="aspectFit" class="icon_title" @click="goCollection">
				</image>
			</block>
			<block></block>
			<block slot="content">
				个人名片
			</block>
		</cu-custom>
		<view class="main">
			<!-- 个人名片展示区域 -->
			<view class="cu-card case">
				<view class="cu-item shadow info">
					<z-swiper ref="zSwiper" v-model="swiperList" @init="init" :option="options" @slideChange="cardSwiper" @nextClick="nextClick" @prevClick="prevClick">
						<z-swiper-item v-for="(item,index) in swiperList" :key="index">
							<view style="height: 420upx;">
								<image class="image"
									:src="(item.bgUrl!='' && item.bgUrl.split('/')[0]=='')?BASE_URL+item.bgUrl:item.bgUrl"
									mode="aspectFill"></image>
							</view>
							<view class="cu-bar my_bar">
								<view class="myCard-item" :style="{color:item.textColor}">
									<view class="icon_item">
										<image class="logo"
											:src="(inputForm.logo!='' && inputForm.logo.split('/')[0]=='')?BASE_URL+inputForm.logo:inputForm.logo"
											mode="aspectFit"></image>
										<text class="gs-name">{{inputForm.company}}</text>
									</view>
									<view class="icon_item">
										<text class="card-user">{{inputForm.name}}</text>
										<text class="position">{{inputForm.position}}</text>
									</view>
									<view class="icon_item">
										<image class="item-img" src="/static/img/dh.png" mode="aspectFit" alt="">
										</image>
										<text>{{inputForm.telephone}}</text>
									</view>
									<view class="icon_item">
										<image class="item-img" src="/static/img/yx.png" mode="aspectFit" alt="">
										</image>
										<text>{{inputForm.email}}</text>
									</view>
									<view class="icon_item">
										<image class="item-img" src="/static/img/dz.png" mode="aspectFit" alt="">
										</image>
										<text>{{inputForm.addressCity+"" +inputForm.addressDetail}}</text>
									</view>
									<view class="photograph">
										<image
											:src="(inputForm.avatar!='' && inputForm.avatar.split('/')[0]=='')?BASE_URL+inputForm.avatar:inputForm.avatar"
											mode="" alt=""></image>
									</view>
								</view>
							</view>
							
						</z-swiper-item>
					</z-swiper>
				</view>
			</view>
			<view class="content">
				<view>
					<uni-forms :modelValue="inputForm" ref="form" :rules="rules" label-position="left"
						label-width="100px">
						<uni-group>
							<uni-forms-item name="company" required label="所属企业">
								<uni-combox :candidates="candidates" placeholder="请选择公司" v-model="inputForm.company"
									@input="getCompany"></uni-combox>
							</uni-forms-item>
							<uni-forms-item name="name" required label="姓名">
								<uni-easyinput type="text" v-model="inputForm.name" placeholder="请输入姓名"></uni-easyinput>
							</uni-forms-item>
							<uni-forms-item name="position" required label="职位:">
								<uni-easyinput type="text" :inputBorder="true" v-model="inputForm.position"
									placeholder="请填写职位"></uni-easyinput>
							</uni-forms-item>
							<uni-forms-item name="telephone" required label="手机号">
								<uni-easyinput type="text" v-model="inputForm.telephone" placeholder="请输入手机号">
								</uni-easyinput>
							</uni-forms-item>
							<uni-forms-item name="WeChat" label="微信号">
								<uni-easyinput type="text" v-model="inputForm.weChat" placeholder="请输入微信号">
								</uni-easyinput>
							</uni-forms-item>
							<uni-forms-item name="email" required label="邮箱">
								<uni-easyinput type="text" v-model="inputForm.email" placeholder="请输入邮箱">
								</uni-easyinput>
							</uni-forms-item>
							</uni-forms-item>
							<uni-forms-item name="avatar" label="头像">
								<view class="uPImg">
									<!-- 上传图片 -->
									<view class="shangchuan">
										<view class="sc2" v-if="inputForm.avatar !=''">
											<image class="del" @click="del(0)" src="/static/img/del.png" mode="">
											</image>
											<image class="Img3"
												:src="(inputForm.avatar!='' && inputForm.avatar.split('/')[0]=='')?BASE_URL+inputForm.avatar:inputForm.avatar"
												mode="" @click="upload"></image>
										</view>
										<view class="sc2" v-else @click="upload">
											<image class="sc3" src="/static/img/upload.png" mode=""></image>
										</view>
									</view>
								</view>
							</uni-forms-item>
							<uni-forms-item name="logo" label="公司logo">
								<view class="uPImg">
									<!-- 上传图片 -->
									<view class="shangchuan">
										<view class="sc2" v-if="inputForm.logo !=''">
											<image class="del" @click="del(1)" src="/static/img/del.png" mode="">
											</image>
											<image class="Img3"
												:src="(inputForm.logo!='' && inputForm.logo.split('/')[0]=='')?BASE_URL+inputForm.logo:inputForm.logo"
												mode="" @click="upload1"></image>
										</view>
										<view class="sc2" v-else @click="upload1">
											<image class="sc3" src="/static/img/upload.png" mode=""></image>
										</view>
									</view>
								</view>
							</uni-forms-item>
							<uni-forms-item name="addressCity" required label="所属城市" v-if="citystate != true">
								<uni-data-picker :localdata="citys" placeholder="请选择所属城市" @change="cityChange"
									v-model="cityDefult" :map="cityName"></uni-data-picker>
							</uni-forms-item>
							<uni-forms-item name="addressDetail" required label="详细地址">
								<uni-easyinput type="text" :disabled="citystate"  :inputBorder="true" v-model="inputForm.addressDetail"
									placeholder="请填写街道信息"></uni-easyinput>
							</uni-forms-item>

						</uni-group>
					</uni-forms>

					<navigator url="pages/legal/contacts" open-type="redirect">
						<view class="cu-bar btn-group  margin-top">
							<button class="cu-btn bg-blue shadow-blur round" @click="submitForm('form')">保存</button>
						</view>
					</navigator>


				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import CardTemplateService from "@/api/mp/CardTemplateService"
	import CardInfoService from "@/api/mp/CardInfoService"
	import EnterpriseService from "@/api/qy/EnterpriseService"
	import UserService from "@/api/sys/UserService"
	import AreaService from "@/api/sys/AreaService"
	import BASE_URL from '@/common/config.js'
	import * as $auth from "@/common/auth"
	import {
		forEach
	} from "lodash"
	export default {
		onLoad(option) {
			if (option.id) {
				new CardInfoService().queryById(option.id).then(data => {
					this.inputForm = data.data
					this.inputForm.logo = this.inputForm.qy.qyLogo
					this.inputForm.state = this.inputForm.qy.state
					let a = this.inputForm.qy.address.split("/")
					this.cityDefult = a[a.length - 1]
					this.inputForm.addressCity = this.inputForm.qy.address
					this.inputForm.addressDetail = this.inputForm.qy.dzDateil
					// if (this.inputForm.avatar == "") {
					// 	this.inputForm.avatar =
					// 		"https://yuleifanhua.oss-cn-shanghai.aliyuncs.com/qymp/def.png"
					// }

				})
			}
		},
		onShow() {
			uni.getUserInfo({
				success(res) {
					console.log(res);
				}
			})
		},
		mounted() {
			uni.showLoading({
				title: '加载中'
			});
			this.getList()
		},
		data() {
			return {
				BASE_URL: BASE_URL,
				inputForm: {
					id: "",
					qy: {
						id: "",
					},
					user: {
						id: "",
					},
					template: {
						id: "",
					},
					type: "",
					logo: "",
					company: "",
					name: "",
					position: "",
					telephone: "",
					weChat: "",
					email: "",
					avatar: "",
					addressCity: "",
					addressDetail: "",
					introduce: "",
					pageviewNum: 0,
					state: 0,
					identity: "",
				},
				userInfo: {
					ip: '88.318.17.316/1452/25',
					isRecord: true,
				},
				// bacImg: "https://yuleifanhua.oss-cn-shanghai.aliyuncs.com/qymp/1/bg.png",
				// textColor: '#fff',
				swiperList: [],
				options: {
					initialSlide: 0,
					speed: 300,
					roundLengths: true,
				},
				dotStyle: false,
				towerStart: 0,
				direction: '',
				rules: {
					name: {
						rules: [{
							required: true,
							errorMessage: '姓名不能为空'
						}]
					},
					email: {
						rules: [{
							required: true,
							errorMessage: '邮箱不能为空'
						}, {
							format: 'email',
							errorMessage: '邮箱格式'
						}]
					},
					company: {
						rules: [{
							required: true,
							errorMessage: '公司不能为空'
						}]
					},
					position: {
						rules: [{
							required: true,
							errorMessage: '职位不能为空'
						}]
					},
					telephone: {
						rules: [{
								required: true,
								errorMessage: '手机号不能为空'
							},
							{
								format: 'number',
								errorMessage: '手机号格式'
							}
						]
					},
					addressCity: {
						rules: [{
							required: true,
							errorMessage: '地址不能为空'
						}]
					},
					addressDetail: {
						rules: [{
							required: true,
							errorMessage: '地址不能为空'
						}]
					},
				},
				candidates: [],
				citys: [],
				cityName: {
					text: 'name',
					value: 'name'
				},
				cityDefult: "",
				companys: [],
				isGo: false,
				cardCur: 0,
				// 所属城市以及详细地址状态Citystate：true 所属企业存在，false所属企业不存在
				citystate:true,
				
			};
		},
		methods: {
			/*获取信息 */
			getList() {
				// 获取城市信息
				new AreaService().treeData().then(data => {
					this.citys = data.data
				})
				//获取名片模板
				new CardTemplateService().list1().then(data => {

					if (data.data.length > 0) {
						this.swiperList = data.data
						if (this.inputForm.template.id == "") {
							this.inputForm.template.id = this.swiperList[0].id
						} else {
							this.swiperList.forEach((item, index) => {
								if (item.id == this.inputForm.template.id) {
									this.cardCur = index
								}
							})
						}
					}

				})
				//获取企业列表
				new EnterpriseService().qyList({
					'current': 1,
					'size': 10000
				}).then(data => {
					this.companys = data.data.records
					this.companys.forEach(item => {
						this.candidates.push(item.name)
					})
					// this.candidates = Object.assign([], data.data.records)
				})
				uni.hideLoading();

			},
			getCompany(value) {
				this.inputForm.company = value
				let company = this.companys.filter(item => item.name == value)
				if (company.length > 0) {
					this.citystate = true;
					this.inputForm.qy.id = company[0].id
					this.inputForm.logo = company[0].qyLogo
					this.inputForm.qy.state = company[0].state
					this.inputForm.state = company[0].state
					this.inputForm.addressCity = company[0].address
					let a = company[0].address.split("/")
					if(a.length > 0) this.cityDefult = a[a.length - 1]
					this.inputForm.addressDetail = company[0].dzDateil
				} else {
					this.citystate = false;
					this.inputForm.qy.id = ""
					this.inputForm.logo = ""
					this.inputForm.qy.state = "0"
					this.inputForm.state = "0"
					this.cityDefult = ""
					this.inputForm.addressCity = ""
					this.inputForm.addressDetail = ""
				}
			},
			cardSwiper(swiper, index) {
				this.inputForm.template.id = this.swiperList[index].id
			},
			nextClick(){
				this.$refs.zSwiper.swiper.slideNext();
			},
			prevClick(){
				this.$refs.zSwiper.swiper.slidePrev();
			},
			init(){
				this.$refs.zSwiper.swiper.slideTo(this.cardCur, 1000, false);
			},
			cityChange: function(e) {
				let c = [];
				e.detail.value.forEach(item => {
					c.push(item.value)
				})
				this.inputForm.addressCity = c.join("/");
			},
			// 头像
			upload() {
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: res => {
						var imgText = '';
						if (res.tempFilePaths.length != 0) {
							imgText = res.tempFilePaths[0]
						}
						var tempFilePaths = res.tempFilePaths;
						uni.uploadFile({
							url: BASE_URL +
								'/sys/file/webupload/upload?uploadPath=mp/card/cardInfo',
							filePath: tempFilePaths[0],
							name: 'file',
							header: {
								'token': this.$auth.getUserToken()
							},
							success: uploadFileRes => {
								this.inputForm.avatar = JSON.parse(uploadFileRes.data).url
							},
							fail(err) {
								console.log("上传失败", err);
							}
						});

					}
				});
			},
			// logo
			upload1() {
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: res => {
						var imgText = '';
						if (res.tempFilePaths.length != 0) {
							imgText = res.tempFilePaths[0]
						}
						var tempFilePaths = res.tempFilePaths;
						uni.uploadFile({
							url: BASE_URL +
								'/sys/file/webupload/upload?uploadPath=mp/card/cardInfo',
							filePath: tempFilePaths[0],
							name: 'file',
							header: {
								'token': this.$auth.getUserToken()
							},
							success: uploadFileRes => {
								console.log('上传图片', JSON.parse(uploadFileRes.data));
								this.inputForm.logo = JSON.parse(uploadFileRes.data).url
							},
							fail(err) {
								console.log("上传失败", err);
							}
						});

					}
				});
			},
			// 删除图片
			del(type) {
				if (type == 0) {
					this.inputForm.avatar = ''
				} else {
					this.inputForm.logo = ''
				}
			},
			// 名片夹
			goCollection() {
				uni.reLaunch({
					url: "/pages/indexInfo/indexInfo"
				})
			},
			// 去认证
			goFirm(id) {

				uni.navigateTo({
					url: '/pages/legal/firm?id=' + id
				})
			},

			submitForm(ref) {
				let that = this
				this.$refs[ref].validate().then(res => {
					uni.showToast({
						title: `校验通过`
					})
					// if (that.inputForm.avatar == "") {
					// 	that.inputForm.avatar =
					// 		"https://yuleifanhua.oss-cn-shanghai.aliyuncs.com/qymp/def.png";
					// }
					if (that.inputForm.qy.state == "1" && that.inputForm.qy.id != "") {
						that.inputForm.state = 1
					}
					if ($auth.getUserInfo().mobile == that.inputForm.telephone) {
						that.inputForm.identity = $auth.getUserInfo().id
						that.inputForm.user.id = $auth.getUserInfo().id
						if (that.inputForm.state == 1) {
							new CardInfoService().save(that.inputForm).then(res1 => {
								uni.showToast({
									title: `联系管理员审核`,
									icon: 'succes',
									success() {
										uni.reLaunch({
											url: "/pages/indexInfo/indexInfo"
										})
									}
								})
							})
						} else if (that.inputForm.state == 0 && that.inputForm.qy.id == "") {
							let qy = {
								name: that.inputForm.company,
								qyImg: "",
								qyLogo: that.inputForm.logo,
								introduce: "",
								lxr: that.inputForm.name,
								lxrPhone: that.inputForm.telephone,
								address: that.inputForm.addressCity,
								dzDateil: that.inputForm.addressDetail,
								longitude: "",
								latitude: "",
								llNum: "0",
								scNum: "0",
								state: "0",
								effectivePeriod: "1",
								endTime: "",
							}
							new EnterpriseService().save(qy).then(data => {
								that.inputForm.qy.id = data.data.id
								new CardInfoService().save(that.inputForm).then(res1 => {
									uni.showToast({
										title: `保存成功`,
										icon: 'succes',
										success() {
											that.goFirm(data.data.id);
										}
									})
								})
							})

						} else {
							new CardInfoService().save(that.inputForm).then(res => {
								uni.showToast({
									title: `保存成功`,
									icon: 'succes',
									success() {
										uni.reLaunch({
											url: "/pages/indexInfo/indexInfo"
										})
									}
								})

							})
						}
					} else {
						let userinfo1 = {}
						if (that.inputForm.user.id == "") {
							userinfo1 = {
								id: '',
								companyDTO: { // 归属公司
									id: that.inputForm.qy.id
								},
								roleIdList: ['1583364965908434945'],
								postIdList: [],
								loginName: that.inputForm.telephone, // 登录名
								no: '', // 工号
								name: that.inputForm.name, // 姓名
								email: '', // 邮箱
								phone: that.inputForm.telephone, // 电话
								mobile: that.inputForm.telephone, // 手机
								loginFlag: '1', // 是否允许登陆
								photo: that.inputForm.avatar, // 头像
								qrCode: '', // 二维码
								oldLoginName: '', // 原登录名
								newPassword: that.inputForm.telephone, // 新密码
								confirmNewPassword: '',
								sign: '', // 签名
								remarks: '' // 备注
							}
							new UserService().save1(userinfo1).then(res => {
								that.inputForm.identity = $auth.getUserInfo().id
								that.inputForm.user.id = res.data.id
								if (that.inputForm.state == 1) {
									new CardInfoService().save(that.inputForm).then(res1 => {
										uni.showToast({
											title: `联系管理员审核`,
											icon: 'succes',
											success() {
												uni.reLaunch({
													url: "/pages/indexInfo/indexInfo"
												})
											}
										})
									})
								} else if (that.inputForm.state == 0 && that.inputForm.qy.id == "") {
									let qy = {
										name: that.inputForm.company,
										qyImg: "",
										qyLogo: that.inputForm.logo,
										introduce: "",
										lxr: that.inputForm.name,
										lxrPhone: that.inputForm.telephone,
										address: that.inputForm.addressCity,
										dzDateil: that.inputForm.addressDetail,
										longitude: "",
										latitude: "",
										llNum: "0",
										scNum: "0",
										state: "0",
										effectivePeriod: "1",
										endTime: "",
									}
									new EnterpriseService().save(qy).then(data => {
										that.inputForm.qy.id = data.data.id
										new CardInfoService().save(that.inputForm).then(res1 => {
											uni.showToast({
												title: `保存成功`,
												icon: 'succes',
												success() {
													that.goFirm(data.data.id);
												}
											})
										})
									})

								} else {
									new CardInfoService().save(that.inputForm).then(res => {
										uni.showToast({
											title: `保存成功`,
											icon: 'succes',
											success() {
												uni.reLaunch({
													url: "/pages/indexInfo/indexInfo"
												})
											}
										})

									})
								}
							})
						} else {
							that.inputForm.identity = $auth.getUserInfo().id
							if (that.inputForm.state == 1) {
								new CardInfoService().save(that.inputForm).then(res1 => {
									uni.showToast({
										title: `联系管理员审核`,
										icon: 'succes',
										success() {
											uni.reLaunch({
												url: "/pages/indexInfo/indexInfo"
											})
										}
									})
								})
							} else if (that.inputForm.state == 0 && that.inputForm.qy.id == "") {
								let qy = {
									name: that.inputForm.company,
									qyImg: "",
									qyLogo: that.inputForm.logo,
									introduce: "",
									lxr: that.inputForm.name,
									lxrPhone: that.inputForm.telephone,
									address: that.inputForm.addressCity,
									dzDateil: that.inputForm.addressDetail,
									longitude: "",
									latitude: "",
									llNum: "0",
									scNum: "0",
									state: "0",
									effectivePeriod: "1",
									endTime: "",
								}
								new EnterpriseService().save(qy).then(data => {
									that.inputForm.qy.id = data.data.id
									new CardInfoService().save(that.inputForm).then(
										res1 => {
											uni.showToast({
												title: `保存成功`,
												icon: 'succes',
												success() {
													that.goFirm(data.data.id);
												}
											})
										})
								})

							} else {
								new CardInfoService().save(that.inputForm).then(res => {
									uni.showToast({
										title: `保存成功`,
										icon: 'succes',
										success() {
											uni.reLaunch({
												url: "/pages/indexInfo/indexInfo"
											})
										}
									})

								})
							}
						}
					}
				}).catch(err => {
					console.log('err', err);
				})
			},
		},

	}
</script>

<style lang="less">
	.image {
		height: 100%;
		width: 100%;
	}

	.icon_title {
		width: 40rpx;
		height: 40rpx;
		margin-left: 20rpx;
	}

	.cu-item {
		padding: 0 20rpx;
		position: relative;
	}

	.c_content {
		position: absolute;
		top: 0;
		left: 0;
		color: #fff;
		padding: 10rpx;
		height: 400px;

		.action image {
			width: 40rpx;
			height: 40rpx;
			margin: 10rpx;
			vertical-align: middle;
		}
	}

	.main {
		background: #fff;

		.my_bar {
			position: absolute;
			width: 100%;
			height: 100%;
			top: 0rpx;
			padding-left: 40rpx;
		}

		.content {
			padding: 20rpx;

			uni-forms-item button {
				font-size: 33rpx;
			}
		}

		.ability {
			display: flex;
		}

		.info-item {
			flex: 1;
			margin: 6rpx 10rpx;
			background-color: #fff;
			border-radius: 10rpx;
			text-align: center;
			padding: 6rpx;
			font-size: 20rpx;
			box-shadow: 2rpx 2rpx 10rpx #bbb;
		}

		.info-item image {
			vertical-align: middle;
			width: 50rpx;
			height: 50rpx;
			margin-bottom: 10rpx;
		}

		.text-cut image {
			vertical-align: -8rpx;
			width: 40rpx;
			height: 40rpx;
			margin-right: 20rpx;
		}

		.cu-item {
			padding: 0 20rpx;
			position: relative;
		}

		.cu-item .desc-gj {
			width: 160rpx;
			height: 50rpx;
		}

		.cu-item .desc-ba {
			width: 120rpx;
			height: 120rpx;
			position: absolute;
			top: 20%;
			right: 1%;
		}

		.keyShow {
			margin: 20rpx 0px;

			.cu-capsule {
				margin: 20rpx;
			}
		}

		.photograph {
			position: absolute;
			top: 80rpx;
			right: 30rpx;
			width: 140rpx;
			height: 170rpx;
		}
	}

	.qy-text {
		text-align: end;
		color: #0081ff;
		margin: 10px;
		font-size: 13px;
	}

	.template_text {
		text-align: center;
		position: absolute;
		top: 40%;
		left: 40%;
	}

	.shangchuan {
		width: 90%;
		height: 200rpx;
		margin: 0 auto;
		display: flex;
		align-items: center;

	}

	.details {
		width: 100%;
		height: 500rpx;
		border: 1px solid #ccc;
		overflow-y: auto;
	}

	.sc2 {
		width: 30%;
		height: 90%;
		border-radius: 10rpx;
		/* background-color: #dadfef; */
		text-align: center;
		line-height: 240rpx;
		margin: 0 10rpx;
		position: relative;
		background-color: #F5F5F5;
	}

	.Img3 {
		width: 100%;
		height: 100%;
		border-radius: 10rpx;
	}

	.del {
		width: 32rpx;
		height: 32rpx;
		position: absolute;
		z-index: 3;
		top: 0rpx;
		right: 0;
	}

	.sc3 {
		width: 96rpx;
		height: 96rpx;
		border-radius: 10rpx;
	}

	.selectBorder {
		padding-left: 10px;
		height: 40px;
		line-height: 40px;
		border: 1px solid #e5e5e5;
	}

	.sBorder {
		border: 1px solid #e5e5e5;
	}

	.main .uni-combox__selector {
		// padding: 12px;
		background-color: #FFFFFF;
		z-index: 10;
	}

	.result-box {
		text-align: center;
		padding: 20px 0px;
		font-size: 16px;
	}
	.position{
		display: inline-block;
		width: 150px !important;
	}
</style>