<template>
	<div>
		<!--面包屑导航-->
		<Breadcrumb parentTitle="博客管理"/>

		<el-form :model="form" :rules="formRules" ref="formRef" label-position="top">
			<el-row :gutter="20">
				<el-col :span="12">
					<el-form-item label="标题" prop="title">
						<el-input v-model="form.title" placeholder="请输入标题"></el-input>
					</el-form-item>
				</el-col>
			</el-row>

			<el-form-item label="描述" prop="description">
				<div id="vditor-description"></div>
			</el-form-item>

			<el-form-item label="正文" prop="content">
				<div id="vditor-content"></div>
			</el-form-item>

			<el-row :gutter="20">
				<el-col :span="12">
					<el-form-item label="分类" prop="cate">
						<el-select v-model="form.cate" placeholder="请选择分类（输入可添加新分类）" :allow-create="true" :filterable="true" style="width: 100%;">
							<el-option :label="item.name" :value="item.id" v-for="item in categoryList" :key="item.id"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="标签" prop="tagList">
						<el-select v-model="form.tagList" placeholder="请选择标签（输入可添加新标签）" :allow-create="true" :filterable="true" :multiple="true" style="width: 100%;">
							<el-option :label="item.name" :value="item.id" v-for="item in tagList" :key="item.id"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
			</el-row>

			<el-form-item style="text-align: right;">
				<el-button type="primary" @click="dialogVisible=true">保存</el-button>
			</el-form-item>
		</el-form>

		<!--编辑可见性状态对话框-->
		<el-dialog title="博客可见性" width="30%" :visible.sync="dialogVisible">
			<!--内容主体-->
			<el-form label-width="50px" @submit.native.prevent>
				<el-form-item>
					<el-radio-group v-model="radio">
						<el-radio :label="1">公开</el-radio>
						<el-radio :label="2">私密</el-radio>
						<!-- <el-radio :label="3">密码保护</el-radio> -->
					</el-radio-group>
				</el-form-item>
				<el-form-item v-if="radio!==2">
					<el-row>
						<el-col :span="6">
							<el-switch v-model="form.recommend" active-text="推荐"></el-switch>
						</el-col>
						<el-col :span="6">
							<el-switch v-model="form.top" active-text="置顶"></el-switch>
						</el-col>
					</el-row>
				</el-form-item>
			</el-form>
			<!--底部-->
			<span slot="footer">
				<el-button @click="dialogVisible=false">取 消</el-button>
				<el-button type="primary" @click="submit">保存</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	import Breadcrumb from "@/components/Breadcrumb";
	import {getCategoryAndTag, saveBlog, getBlogById, updateBlog} from '@/api/blog'
	import Vditor from 'vditor'

	export default {
		name: "WriteBlog",
		components: {Breadcrumb},
		data() {
			return {
				ready: false,
				descriptionVditor: null,
				contentVditor: null,
				categoryList: [],
				tagList: [],
				dialogVisible: false,
				radio: 1,
				form: {
					title: '',
					firstPicture: '',
					description: '',
					content: '',
					cate: null,
					tagList: [],
					words: null,
					readTime: null,
					views: 0,
					appreciation: false,
					recommend: false,
					commentEnabled: false,
					top: false,
					published: false,
					password: '',
				},
				formRules: {
					title: [{required: true, message: '请输入标题', trigger: 'change'}],
					//firstPicture: [{required: true, message: '请输入首图链接', trigger: 'change'}],
					cate: [{required: true, message: '请选择分类', trigger: 'change'}],
					// tagList: [{required: true, message: '请选择标签', trigger: 'change'}],
				},
			}
		},
		created() {
			this.getData()
		},
		mounted() {
			this.initDescriptionVditor()
			this.initContentVditor()
		},
		methods: {
			//初始化md编辑器
			initDescriptionVditor() {
				const options = {
					height: 320,
					mode: 'sv',//分屏渲染
					outline: true,//大纲
					cache: {//不缓存到localStorage
						enable: false,
					},
					resize: {//可调整高度
						enable: true
					},
					after: () => {
						if (!this.ready) {
							this.ready = true //两个编辑器都加载完成后执行，避免重复请求
						} else if (this.$route.params.id) {
							this.getBlog(this.$route.params.id)
						}
					}
				}
				this.descriptionVditor = new Vditor('vditor-description', options)
			},
			initContentVditor() {
				const options = {
					height: 640,
					mode: 'sv',//分屏渲染
					outline: true,//大纲
					cache: {//不缓存到localStorage
						enable: false,
					},
					resize: {//可调整高度
						enable: true
					},
					after: () => {
						if (!this.ready) {
							this.ready = true
						} else if (this.$route.params.id) {
							this.getBlog(this.$route.params.id)
						}
					}
				}
				this.contentVditor = new Vditor('vditor-content', options)
			},
			getBlog(id) {
				//编辑博客
				getBlogById(id).then(res => {
					if (res.code === 200) {
						this.computeCategoryAndTag(res.data)
						// this.msgSuccess(res.msg);
						this.form = res.data
						this.descriptionVditor.setValue(this.form.description)
						this.contentVditor.setValue(this.form.content)
						this.radio = this.form.published ? (this.form.password !== '' ? 3 : 1) : 2
					} else {
						this.msgError(res.msg)
					}
				}).catch(() => {
					this.msgError('请求失败')
				})
			},
			computeCategoryAndTag(blog) {
				blog.cate = blog.category.id
				blog.tagList = []
				blog.tags.forEach(item => {
					blog.tagList.push(item.id)
				})
			},
			getData() {
				//获取目录和标签
				getCategoryAndTag().then(res => {
					if (res.code === 200) {
						// this.msgSuccess(res.msg);
						this.categoryList = res.data.categories
						this.tagList = res.data.tags
					} else {
						this.msgError(res.msg)
					}
				}).catch(() => {
					this.msgError('请求失败')
				})
			},
			submit() {
				this.$refs.formRef.validate(valid => {
					if (valid) {
						this.form.description = this.descriptionVditor.getValue()
						this.form.content = this.contentVditor.getValue()
						if (this.radio === 2) {
							this.form.appreciation = false
							this.form.recommend = false
							this.form.commentEnabled = false
							this.form.top = false
							this.form.published = false
						} else {
							this.form.published = true
						}
						if (this.radio !== 3) {
							this.form.password = ''
						}
						if (this.$route.params.id) {
							this.form.category = null
							this.form.tags = null
							updateBlog(this.form).then(res => {
								if (res.code === 200) {
									this.msgSuccess(res.msg)
									this.$router.push('/blogs')
								} else {
									this.msgError(res.msg)
								}
							}).catch(() => {
								this.msgError('请求失败')
							})
						} else {
							saveBlog(this.form).then(res => {
								if (res.code === 200) {
									this.msgSuccess(res.msg)
									this.$router.push('/blogs')
								} else {
									this.msgError(res.msg)
								}
							}).catch(() => {
								this.msgError('请求失败')
							})
						}
					} else {
						this.dialogVisible = false
						return this.msgError('请填写必要的表单项')
					}
				})
			}
		}
	}
</script>

<style scoped>
	@import "~vditor/dist/index.css";
</style>