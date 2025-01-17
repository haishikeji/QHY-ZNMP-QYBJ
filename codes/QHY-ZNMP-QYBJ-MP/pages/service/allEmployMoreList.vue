<template>
	<view>
		<cu-custom bgColor="bg-white" :isBack="true">
			<block slot="content">
				{{title}}
			</block>
		</cu-custom>
		<view class="Grid" v-if="type==1">
			<view class="Grid-Item" @click="ToInfo(item)" v-for="item in dataList" :key="item.id">
				<view class="Grid-f1" style="">
					<image class="Grid-im" v-if="item.typeSd=='1'" src= "/static/img/tip_g.png"></image>
					<image class="Grid-im" v-if="item.typeSd=='2'" src= "/static/img/tip_x.png"></image>

					<view class="Grid-title">{{ item.title }}</view>
				</view>
				<view class="Grid-t2">{{ item.industryType.name }}</view>
				<view class="Grid-t3">{{ item.description }}</view>
				<view class="Grid-t4"><text >预算金额:</text><text class="Grid-t5">{{ item.budgetAmount }}</text></view>
				<view class="GStitle21">{{ item.enterpriseInfo.name }}</view>
				<view class="Grid-t6">{{ item.createDate }}</view>
				<view>
					<view>
						<view class="Grid-f2">
							<image class="Grid-im2" src="/static/img/tip_s.png" mode="aspectFit"></image>
							<text class="Grid-t7">收藏</text>

							<image class="Grid-im2"src="/static/img/tip_l.png" mode="aspectFit"></image>
							<text class="Grid-t7">留言</text>
						</view>
					</view>
				</view>
			</view>
		</view>


		<view class="Grid" v-if="type==2">
			<view class="Grid-Item" @click="ToInfo2(item)" v-for="item in dataList" :key="item.id">
				<view class="Grid-f1" style="">
					<image class="Grid-im" v-if="item.typeSd=='1'" src= "/static/img/tip_g.png"></image>
					<image class="Grid-im" v-if="item.typeSd=='2'" src= "/static/img/tip_x.png"></image>
					<view class="Grid-title">{{ item.title }}</view>
				</view>
				<view class="Grid-t2">{{ item.industryType.name }}</view>
				<view class="Grid-t3">{{ item.description }}</view>
				<view class="Grid-t4"><text >预算金额:</text><text class="Grid-t5">{{ item.budgetAmount }}</text></view>
				<view class="GStitle21">{{ item.enterpriseInfo.name }}</view>
				<view class="Grid-t6">{{ item.createDate }}</view>
				<view>
					<view>
						<view class="Grid-f2">
							<image class="Grid-im2" src="/static/img/tip_s.png" mode="aspectFit"></image>
							<text class="Grid-t7">收藏</text>

							<image class="Grid-im2"src="/static/img/tip_l.png" mode="aspectFit"></image>
							<text class="Grid-t7">留言</text>
						</view>
					</view>
				</view>
			</view>
		</view>


		<view class="Grid" v-if="type==3">
			<view class="Grid-Item2" @click="ToInfo3(item)" v-for="item in dataList" :key="item.id">
			    <view class="Grid-f3">
					<view class="Grid-t8" v-if="item.area=='1'">[省]</view>
					<view class="Grid-t8" v-if="item.area=='2'">[市]</view>
					<view class="Grid-t8" v-if="item.area=='3'">[区]</view>
					<view class="Grid-t9">{{ item.title }}</view>
				</view>
				<view class="Grid-t4">政策标签：{{ item.marks }}</view>
				<view class="Grid-f4">
					<view class="Grid-t4">发文部门：{{ item.docDepart }}</view>
					<view class="Grid-t10">{{ item.createDate }}</view>
				</view>

			</view>
		</view>


		<view class="Grid" v-if="type==4">
			<view class="Grid-Item3" @click="ToInfo6(item)" v-for="item in dataList" :key="item.id">
				<view class="Grid-f1" style="">
					<image class="Grid-im" src= "/static/img/tip_g1.png"></image>
					<view class="Grid-title">{{ item.title }}</view>
				</view>
				<view class="Grid-t2">{{ item.marks }}</view>
				<view class="Grid-t3">{{ item.description }}</view>
				<view class="GStitle21">{{ item.enterpriseInfo.name }}</view>
				<view class="Grid-t6">{{ item.createDate }}</view>
				<view>
					<view>
						<view class="Grid-f2">
							<image class="Grid-im2" src="/static/img/tip_s.png" mode="aspectFit"></image>
							<text class="Grid-t7">收藏</text>

							<image class="Grid-im2"src="/static/img/tip_l.png" mode="aspectFit"></image>
							<text class="Grid-t7">留言</text>
						</view>
					</view>
				</view>
			</view>
		</view>


		<view class="Grid" v-if="type==5">
			<view class="Grid-Item" @click="ToInfo4(item)" v-for="item in dataList" :key="item.id">

				<image class="Grid-im3" :src= item.imgs mode="aspectFill" ></image>
				<view class="Grid-title">{{ item.title }}</view>
				<view class="Grid-t3">{{ item.description }}</view>
			</view>
		</view>


		<view class="Grid" v-if="type==6">
			<view class="Grid-Item4" @click="ToInfo5(item)" v-for="item in dataList" :key="item.id">
			    <image mode="aspectFit" class="Grid-im4" :src= item.imgs ></image>
				<view class="Grid-f5" style="flex: 6;">

					<view >
						<view class="Grid-t8">[{{ item.title }}]</view>
						<view class="Grid-t3">{{ item.description }}</view>
					</view>

				</view>

			</view>
		</view>

		<view class="Grid" v-if="type==7">
			<view class="Grid-Item11" @click="ToInfo7(item)" v-for="item in dataList" :key="item.id">
				<view class="f1" style="">
					<image class="im" src= "/static/img/tip_g.png"></image>
					<view class="GStitle">{{ item.title }}</view>
				</view>
				<view class="GStitle3">{{ item.description }}</view>
				<view class="GStitle3xian">{{ item.createDate }}</view>
				<view>
					<view>
						<view class="action2">
							<image class="im2" src="/static/img/tip_s.png" mode="aspectFit"></image>
							<text class="GStitle2_1">收藏</text>

							<image class="im2"src="/static/img/tip_l.png" mode="aspectFit"></image>
							<text class="GStitle2_1">留言</text>
						</view>
					</view>
				</view>
			</view>
		</view>

		<view class="isOver" v-if="isOver">------我是有底线的------</view>
	</view>
</template>

<script>
	import AllEmployService from '@/api/fw/AllEmployService.js'
	import BASE_URL from '@/common/config.js'
	export default {
		onLoad(option){
			this.type = option.type;
			if(this.type==1){
				this.title="产品供需";
			}else if(this.type==2){
				this.title="人才供需";
			}else if(this.type==3){
				this.title="政策推送";
			}else if(this.type==4){
				this.title="产能共享";
			}else if(this.type==5){
				this.title="解决方案";
			}else if(this.type==6){
				this.title="银企对接";
			}else if(this.type==7){
				this.title="工业APP";
			}
		},
		onShow() {
			uni.getUserInfo({
				success(res) {
					console.log(res);
				}
			})
			this.getFWList();
		},
		data() {
			return {
				dataList: [],
				pages:0,
				title:"更多服务",
				type:"1",
				pageSize:10,
				currentPage:1,
				isOver:false,
			}
		},
		methods:{
			getFWList() {
			  this.loading = true
			  new AllEmployService().list({
				  'current': this.currentPage,
				  'size': this.pageSize,
				  'type': this.type,
				  'typeSd': '',
				  'industryType.id': '',
				  'title': '',
				  'description': '',
				  'area': '',
				  'marks': '',
				  'docDepart': '',
				  'state': '1',
				  'ismobile':'1',
				  ...this.searchForm
				}).then(({data}) => {
				  let obj=data;
				  if(obj.total>0){
					  for (let i = 0; i < obj.records.length; i++) {
						  obj.records[i].imgs =BASE_URL+ obj.records[i].imgs;
					  }
					  //this.dataList=obj.records;

					  this.dataList = [...this.dataList, ...obj.records];
					  this.pages=obj.pages;
				  }
				  this.loading = false

				})

			},
			//产品供需详情
			ToInfo(e){
				uni.navigateTo({
					url:'/pages/service/productInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			//人才供需详情
			ToInfo2(e){
				uni.navigateTo({
					url:'/pages/service/personInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			//政策推送详情
			ToInfo3(e){
				uni.navigateTo({
					url:'/pages/service/policyPushInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			//解决方案详情11
			ToInfo4(e){
				uni.navigateTo({
					url:'/pages/service/solutionInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			//银企对接详情
			ToInfo5(e){
				uni.navigateTo({
					url:'/pages/service/bankEntConInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			//产能共享详情
			ToInfo6(e){
				uni.navigateTo({
					url:'/pages/service/capacitySharingInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			ToInfo6(e){
				uni.navigateTo({
					url:'/pages/service/IndustrialAppInfo?obj='+ encodeURIComponent(JSON.stringify(e))
				})
			},

			// 下拉到底部后加载新数据
			onReachBottom() {
			  if (this.currentPage < this.pages) {
				  this.currentPage++;
				  this.getFWList();
			  }else{
				  this.isOver = true;
			  }

			},
		},
}
</script>

<style>
	.Grid {
		margin-top: 10rpx;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		align-content: space-between;
		padding:10rpx 0;

	}
	.Grid-Item {
		width: 48%;
		height: 360rpx;
		text-align: center;
		margin-left: 5rpx;
		margin-right: 5rpx;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		background: #f7f7f7;
		box-sizing:border-box;
	}
	.Grid-Item2 {
		width: 100%;
		height: 188rpx;
		text-align: center;
		margin-left: 5rpx;
		margin-right: 5rpx;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		background: #f7f7f7;
		box-sizing:border-box;
	}
	.Grid-Item3 {
		width: 48%;
		height: 310rpx;
		text-align: center;
		margin-left: 5rpx;
		margin-right: 5rpx;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		background: #f7f7f7;
		box-sizing:border-box;
	}
	.Grid-Item4 {
		display: flex;
		flex-direction: row;
		align-items: center;
		padding-left: 10rpx;
		width: 100%;
		height: 188rpx;
		text-align: center;
		margin-left: 5rpx;
		margin-right: 5rpx;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		background: #f7f7f7;
		box-sizing:border-box;
		}
	.Grid-im{
		width: 40rpx;
		height: 40rpx;
	}
	.Grid-im2{
		margin-top: 15rpx;
		width: 26rpx;
		height: 26rpx;
	}
	.Grid-im3{
		width: 330rpx;
		height: 200rpx;
	}
	.Grid-im4{
		width: 158rpx;
		height: 158rpx;
	}
	.Grid-f1{display: flex; flex-direction:row-reverse;justify-content: space-between;}
	.Grid-f2{display: flex;justify-content: center;align-items: flex-end;}
	.Grid-f3{margin-top: 20rpx;display: flex;}
	.Grid-f4{display: flex;justify-content: space-between;}
	.Grid-f5{display: flex;	}
	.Grid-title {
		text-align: left;
		color: #06121e;
		padding-left: 20rpx;
		font-size: 13px;
		font-weight: 600;
		font-style: ;
		margin-top: 20rpx;
	}
	.Grid-t2 {
		text-align: left;
		color: #1699c8;
		width: auto;
		padding-left: 20rpx;
		font-size: 8px;
		font-weight: 500;
		margin-top: 20rpx;
	}
	.GStitle21 {
		text-align: left;
		color: #1699c8;
		width: auto;
		padding-left: 20rpx;
		font-size: 8px;
		font-weight: 500;
		margin-top: 20rpx;
	}
	.Grid-t3 {
		text-align: left;
		padding-left: 20rpx;
		font-size: 10px;
		margin-top: 20rpx;
		min-height: 60rpx;
		overflow: hidden;
		-webkit-line-clamp: 2;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-box-orient: vertical;
	}
	.Grid-t4 {
		text-align: left;
		font-size: 10px;
		font-style: ;
		margin-top: 20rpx;
		padding-left: 20rpx;
	}
	.Grid-t5 {
		color: #ff0000;
		text-align: left;
		font-size: 10px;
		font-style: ;
		margin-top: 20rpx;
	}
	.Grid-t6 {
		text-align: left;
		padding-left: 20rpx;
		padding-bottom: 20rpx;
		font-size: 10px;
		margin-top: 20rpx;
		border-bottom: 1px dashed #ccc;
	}
	.Grid-t7 {
		text-align: left;
		color: #1699c8;
		border: #1699c8;
		padding-top: 12rpx;
		padding-left: 10rpx;
		padding-right: 30rpx;
		font-size: 9px;
		font-weight: 500;
	}
	.Grid-t8 {
		text-align: left;
		color: #1699c8;
		padding-left: 20rpx;
		font-size: 13px;
		font-weight: 600;
		font-style: ;
		margin-top: 20rpx;
	}
	.Grid-t9 {
		text-align: left;
		color: #06121e;
		font-size: 13px;
		font-weight: 600;
		font-style: ;
		margin-top: 20rpx;
		overflow: hidden;
		-webkit-line-clamp: 1;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-box-orient: vertical;
	}
	.Grid-t10 {
		padding-right: 20rpx;
		text-align: left;
		color: #06121e;
		font-size: 13px;
		font-weight: 500;
		font-style: ;
		margin-top: 20rpx;
	}

	.isOver {
	  margin: 0 auto;
	  width: 100%;
	  padding: 5px;
	  display: flex;
	  justify-content: center;
	}


</style>
