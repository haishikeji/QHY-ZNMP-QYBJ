<template>
	<view>
		<cu-custom bgColor="bg-white" :isBack="true">
			<block slot="content">个人名片</block>
		</cu-custom>
		<view class="cu-list">
			<view class="cu-item" v-for="(item,index) in myCards" :key="index" @click="checkCard">
				<view class="cu-card case">
					<view class="cu-item shadow info">
						<view class="image">
							<image src="/static/img/bg.png" mode="widthFix">
							</image>
							<view class="cu-bar">
								<view class="myCard-item">
									<view> 
										<text>{{item.icon}}</text>
										<text style="font-weight: 700;">{{item.corporateName}}</text>
										<image class="authentication" :src="item.state==0?'/static/img/qy_wrz.png'
												:item.state == 1?'/static/img/qy_yrz.png'
												:item.state == 2?'/static/img/sm_wrz.png'
												:item.state == 3?'/static/img/sm_yrz.png'
												:'#'
												" mode="aspectFit"></image>
									</view>
									<view>
										<text class="card-user">{{item.name}}</text>
										<text>{{item.position}}</text>
									</view>
									<view>
										<image class="item-img" src="/static/img/dh.png" mode="aspectFit" alt=""></image>
										<text>{{item.tel}}</text>
									</view>
									<view>
										<image class="item-img" src="/static/img/yx.png" mode="aspectFit" alt=""></image>
										<text>{{item.email}}</text>
									</view>
									<view>
										<image class="item-img" src="/static/img/dz.png" mode="aspectFit" alt=""></image>
										<text>{{item.address}}</text>
									</view>
									<view class="photograph">
										<!-- TODO 线上图片 -->
										<image src="https://yuleifanhua.oss-cn-shanghai.aliyuncs.com/qymp/1/person.jpg" mode="aspectFit" alt=""></image>
									</view>
								</view>
							</view>
						</view>
						
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return {
				myCards: [{
					icon: '',
					corporateName: '江苏惠都科技有限公司',
					name: '钱东玲',
					position: '副总经理',
					tel: '17751554188',
					email: 'dl.qian@huidutech.com.cn',
					address: '盐城市盐都区振兴路研创大厦2楼',
					state: '1'
				},
				{
					icon: '',
					corporateName: '江苏惠都科技有限公司',
					name: '钱东玲',
					position: '副总经理',
					tel: '17751554188',
					email: 'dl.qian@huidutech.com.cn',
					address: '盐城市盐都区振兴路研创大厦2楼',
					state: '1'
				},
				{
					icon: '',
					corporateName: '江苏惠都科技有限公司',
					name: '钱东玲',
					position: '副总经理',
					tel: '17751554188',
					email: 'dl.qian@huidutech.com.cn',
					address: '盐城市盐都区振兴路研创大厦2楼',
					state: '1'
				},
				{
					icon: '',
					corporateName: '江苏惠都科技有限公司',
					name: '钱东玲',
					position: '副总经理',
					tel: '17751554188',
					email: 'dl.qian@huidutech.com.cn',
					address: '盐城市盐都区振兴路研创大厦2楼',
					state: '1'
				},
				{
					icon: '',
					corporateName: '江苏惠都科技有限公司',
					name: '钱东玲',
					position: '副总经理',
					tel: '17751554188',
					email: 'dl.qian@huidutech.com.cn',
					address: '盐城市盐都区振兴路研创大厦2楼',
					state: '1'
				},
				{
					icon: '',
					corporateName: '江苏惠都科技有限公司',
					name: '钱东玲',
					position: '副总经理',
					tel: '17751554188',
					email: 'dl.qian@huidutech.com.cn',
					address: '盐城市盐都区振兴路研创大厦2楼',
					state: '1'
				}
				],
			}
		},
		methods:{
			checkCard(){
				uni.navigateTo({
					url:'/pages/card/card'
				})
			}
		}
	}
</script>

<style>
	.icon_title {
		width: 40rpx;
		height: 40rpx;
		margin-left: 20rpx;
	}
	
	.cu-card.case .cu-item {
		padding: 0px;
	}
	
	.cu-card.case .cu-item .image .cu-bar {
		color: white;
		height: 100%;
		top: 0;
	}
	
	.myCard-item {
		padding: 20rpx 40rpx;
	
	}
	
	.cu-card.case .myCard-item .item-img {
		width: 50rpx;
		height: 50rpx;
		/* vertical-align: top; */
		margin-right: 10rpx;
	}
	
	.cu-card.case .myCard-item .authentication {
		width: 120rpx;
		height: 60rpx;
	}
	
	.myCard-item>view {
		margin-bottom: 12rpx;
	}
	
	.myCard-item>view text {
		margin-right: 10rpx;
	}
	
	.card-user {
		font-size: 40rpx;
		font-weight: 700;
		line-height: 80rpx;
	}
	
	.photograph {
		position: absolute;
		top: 90rpx;
		right: 60rpx;
		width: 140rpx;
		height: 180rpx;
		border: 2rpx solid;
	}
	
	.photograph image {
		width: 100%;
		height: 100%;
	}
	
	.cu-card>.info {
		background-color: transparent;
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
	
	.c_content .action image {
		width: 40rpx;
		height: 40rpx;
		margin: 10rpx;
		vertical-align: middle;
	}
	
	.c_content {
		position: absolute;
		top: 0;
		left: 0;
		color: #fff;
		padding: 10rpx;
		height: 400px;
	}
</style>