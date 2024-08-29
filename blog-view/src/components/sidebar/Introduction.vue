<template>
	<div>
		<div class="ui segments m-box">
			<div class="ui card">
				<div class="image">
					<img :src="introduction.avatar">
				</div>
				<div class="content" align="center">
					<div class="header">{{ introduction.name }}</div>
				</div>
				<div>
					<el-collapse accordion>
						<el-collapse-item :title="item.title" :name="index" v-for="(item,index) in introduction.favorites" :key="index">
							<div>{{ item.content }}</div>
						</el-collapse-item>
					</el-collapse>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {mapState} from 'vuex'

	export default {
		name: "Introduction",
		computed: {
			...mapState(['introduction'])
		},
		methods: {
		}
	}
</script>

<style>
	.el-collapse-item {
		padding-left: 15px;
		padding-right: 15px;
	}

	.el-collapse-item .el-collapse-item__content {
		padding-bottom: 10px;
	}
</style>