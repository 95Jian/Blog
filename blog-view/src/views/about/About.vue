<template>
	<div>
		<div class="ui top attached segment m-padded-lr-big">
			<h2 class="m-text-500" style="text-align: center">{{ about.title }}</h2>
			
			<div class="typo content m-margin-top-large" v-viewer v-html="about.content"></div>
		</div>
	</div>
</template>

<script>
	import {getAbout} from "@/api/about";

	export default {
		name: "About",
		data() {
			return {
				about: {
					title: '',
					musicId: '',
					content: '',
					commentEnabled: 'false'
				}
			}
		},
		created() {
			this.getData()
		},
		methods: {
			getData() {
				getAbout().then(res => {
					if (res.code === 200) {
						this.about = res.data
					} else {
						this.msgError(res.msg)
					}
				}).catch(() => {
					this.msgError("请求失败")
				})
			}
		}
	}
</script>

<style>
	.content ul li {
		letter-spacing: 1px !important;
	}
</style>