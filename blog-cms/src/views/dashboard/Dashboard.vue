<template>
	<div>
		<el-row class="panel-group" :gutter="50">
			<el-col :span="6">
				<el-card class="card-panel" body-style="padding: 0">
					<div class="card-panel-icon-wrapper">
						<SvgIcon icon-class="#icon-article" class-name="card-panel-icon"/>
					</div>
					<div class="card-panel-description">
						<div class="card-panel-text">文章数</div>
						<span class="card-panel-num">{{ blogCount }}</span>
					</div>
				</el-card>
			</el-col>
		</el-row>

		<el-row class="panel-group" :gutter="20">
			<el-col :span="8">
				<el-card>
					<div ref="categoryEcharts" style="height:500px;"></div>
				</el-card>
			</el-col>
			<el-col :span="8">
				<el-card>
					<div ref="tagEcharts" style="height:500px;"></div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import SvgIcon from "@/components/SvgIcon";
	import '@/assets/css/icon/iconfont'
	import echarts from 'echarts'
	import 'echarts/map/js/china'
	import {getDashboard} from "@/api/dashboard";

	export default {
		name: "Dashboard",
		components: {SvgIcon},
		data() {
			return {
				blogCount: 0,
				commentCount: 0,
				categoryEcharts: null,
				tagEcharts: null,
				categoryOption: {
					title: {
						text: '分类下文章数量',
						x: 'center'
					},
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b} : {c} ({d}%)'
					},
					legend: {
						left: 'center',
						top: 'bottom',
						data: []
					},
					series: [
						{
							name: '文章数量',
							type: 'pie',
							radius: [30, 110],
							roseType: 'area',
							data: []
						}
					]
				},
				tagOption: {
					title: {
						text: '标签下文章数量',
						x: 'center'
					},
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b} : {c} ({d}%)'
					},
					legend: {
						left: 'center',
						top: 'bottom',
						data: []
					},
					series: [
						{
							name: '文章数量',
							top: '-10%',
							type: 'pie',
							radius: [30, 110],
							roseType: 'area',
							data: []
						}
					]
				}
			}
		},
		mounted() {
			this.getData()
		},
		methods: {
			getData() {
				getDashboard().then(res => {
					if (res.code === 200) {
						this.blogCount = res.data.blogCount
						//渲染分类数据
						this.categoryOption.legend.data = res.data.category.legend
						this.categoryOption.series[0].data = res.data.category.series
						this.initCategoryEcharts()
						//渲染标签数据
						this.tagOption.legend.data = res.data.tag.legend
						this.tagOption.series[0].data = res.data.tag.series
						this.initTagEcharts()
					} else {
						this.msgError(res.msg)
					}
				}).catch(() => {
					this.msgError("请求失败")
				})
			},
			initCategoryEcharts() {
				this.categoryEcharts = echarts.init(this.$refs.categoryEcharts, 'light')
				this.categoryEcharts.setOption(this.categoryOption)
			},
			initTagEcharts() {
				this.tagEcharts = echarts.init(this.$refs.tagEcharts, 'light')
				this.tagEcharts.setOption(this.tagOption)
			},
		}
	}
</script>

<style scoped>
	.panel-group {
		margin-bottom: 30px;
	}

	.panel-group .card-panel {
		height: 108px;
		position: relative;
		overflow: hidden;
	}

	.panel-group .card-panel .card-panel-icon-wrapper {
		float: left;
		margin: 14px 0 0 14px;
		padding: 16px;
	}

	.panel-group .card-panel .card-panel-icon {
		float: left;
		font-size: 48px;
	}

	.panel-group .card-panel .card-panel-description {
		float: right;
		font-weight: 700;
		margin: 26px 26px 26px 0;
	}

	.panel-group .card-panel .card-panel-description .card-panel-text {
		color: rgba(0, 0, 0, 0.45);
		font-size: 16px;
		margin-bottom: 12px;
	}

	.panel-group .card-panel .card-panel-description .card-panel-num {
		font-size: 20px;
	}
</style>