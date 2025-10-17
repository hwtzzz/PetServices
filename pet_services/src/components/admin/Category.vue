<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/" style="height: 35px;" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="'/adminHome'">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ this.$route.meta.title }}</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 搜索内容 -->
        <el-input placeholder="分类名称" prefix-icon="el-icon-search" v-model.trim="categoryName" clearable
            style="width: 200px;"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="search" style="margin-left: 10px;">搜索</el-button>

        <!-- 添加按钮 -->
        <el-button type="primary" icon="el-icon-plus" @click="addNotice()" style="margin-left: 10px;">添加分类</el-button>

        <!-- excel导出 -->
        <el-button type="primary" style="margin-left: 20px;" @click="exportExcelSelect"
            icon="el-icon-folder-add">导出Excel文件</el-button>
        <!--excel导出预览弹窗表格-->
        <el-dialog title="表格保存预览" width="70%" :visible.sync="selectWindow">
            <el-table :data="selectData" id="selectTable" height="380px">
                <el-table-column prop="categoryId" label="分类编号">
                </el-table-column>
                <el-table-column prop="categoryName" label="类型名称">
                </el-table-column>
                <el-table-column prop="categoryDesc" label="类型描述">
                </el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="exportExcel">确定保存</el-button>
            </div>
        </el-dialog>

        <!-- 数据列表 -->
        <el-table :data="tableData" style="width: 100%;margin-top: 10px" border :row-class-name="tableRowClassName"
            stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection">
            </el-table-column>
            <el-table-column prop="categoryImage" label="类型图片" width="120">
                <template slot-scope="scope">
                    <img :src="scope.row.categoryImage" alt="" srcset="" width="90px" height="90px">
                </template>
            </el-table-column>
            <el-table-column prop="categoryId" label="分类编号">
            </el-table-column>
            <el-table-column prop="categoryName" label="类型名称">
            </el-table-column>
            <el-table-column prop="categoryDesc" label="类型描述">
            </el-table-column>

            <!-- 操作 -->
            <el-table-column label="操作" width="300">
                <template slot-scope="scope">
                    <!-- 修改信息按钮 -->
                    <el-button type="primary" size="mini" @click="updateOpen(scope.row)"
                        icon="el-icon-edit">修改分类信息</el-button>

                    <!-- 删除信息按钮 -->
                    <el-popconfirm v-model="visible" title="确定要删除此分类吗？" @confirm='handleConfirm(scope.row)'
                        @cancel='visible = false'>
                        <el-button type="danger" size="mini" slot="reference" style="margin-left: 10px;"
                            icon="el-icon-delete-solid">删除分类</el-button>
                    </el-popconfirm>

                </template>
            </el-table-column>
        </el-table>

        <!-- 添加弹窗 -->
        <el-dialog title="添加分类" :visible.sync="addFormVisible" :show-close="false">
            <el-form :model="addform">
                <el-form-item label="类型名称" :label-width="formLabelWidth">
                    <el-input v-model="addform.categoryName" autocomplete="off" required maxlength="15"
                        show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="类型描述" :label-width="formLabelWidth">
                    <el-input v-model="addform.categoryDesc" autocomplete="off" required type="textarea" maxlength="200"
                        show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="类型图片" :label-width="formLabelWidth">
                    <el-upload action="http://127.0.0.1:9999/upload" list-type="picture-card"
                        :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :on-success="setUrl" :limit="1"
                        ref='upload1'>
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="addform.categoryImage" alt="">
                    </el-dialog>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="addSubmit()">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 修改弹窗 -->
        <el-dialog title="修改分类信息" :visible.sync="updateVisible" :show-close="false">
            <el-form :model="form">
                <el-form-item label="类型名称" :label-width="formLabelWidth">
                    <el-input v-model="form.categoryName" autocomplete="off" required maxlength="20"
                        show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="类型描述" :label-width="formLabelWidth">
                    <el-input v-model="form.categoryDesc" autocomplete="off" type="textarea" required maxlength="200"
                        show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="类型图片" :label-width="formLabelWidth">
                    <el-upload action="http://127.0.0.1:9999/upload" list-type="picture-card"
                        :on-preview="handlePictureCardPreview2" :on-remove="handleRemove2" :on-success="setUrl2"
                        :limit="1" ref='upload2'>
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible2">
                        <img width="100%" :src="form.categoryImage" alt="">
                    </el-dialog>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="updateSubmit()">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import { getAllCategory, deleteCategory, addCategory, updateCategory, searchCategory } from '@/api/CategoryAPI.js'
import htmlToExcel from '@/utils/htmlToExcel.js'
export default {
    name: 'InnovationSystemCategory',

    data() {
        return {
            //表格中选中的数据
            selectData: [],
            selectWindow: false,
            tableData: [],
            categoryName: '',
            addFormVisible: false,
            visible: false,
            dialogFormVisible: false,
            formLabelWidth: '120px',
            dialogVisible: false,
            dialogVisible2: false,
            form: {
                categoryId: '',
                categoryName: '',
                categoryDesc: '',
                categoryImage: ''
            },
            addform: {
                categoryName: '',
                categoryDesc: '',
                categoryImage: ''
            },
            updateVisible: false,
        };
    },

    mounted() {

    },

    methods: {
        quxiao() {
            this.addFormVisible = false
            this.updateVisible = false
            this.$refs.upload1.clearFiles();
            this.$refs.upload2.clearFiles();
        },
        //导出
        exportExcel() {
            htmlToExcel.getExcel('#selectTable', '分类信息')
        },
        //显示预览弹窗
        exportExcelSelect() {
            if (this.selectData.length < 1) {
                this.$message.error('请选择要导出的内容！');
                return false;
            }
            this.selectWindow = true;
        },
        //选中数据
        handleSelectionChange(val) {
            this.selectData = val;
        },
        tableRowClassName({ row, rowIndex }) {
            if (rowIndex === 1) {
                return 'warning-row';
            } else if (rowIndex === 3) {
                return 'success-row';
            }
            return '';
        },
        async updateSubmit() {
            let isEmpty = this.$infoRule(this.form)
            if (isEmpty == true) {
                this.$message.error("分类信息不完整!")
                return;
            }
            let { data: res } = await updateCategory(this.form)
            if (res.success) {
                this.$refs.upload2.clearFiles();
                this.$message.success("分类信息更新成功")
                this.updateVisible = false
            } else {
                this.$message.error(res.msg)
            }
        },
        updateOpen(row) {
            this.form = row
            this.updateVisible = true
        },
        async handleConfirm(row) {
            let { data: res } = await deleteCategory(row.categoryId)
            if (res.success) {
                this.$message.success("删除分类" + row.categoryName + "成功")
                this.tableData = res.result
                this.visible = false
            } else {
                this.$message.error(res.msg)
            }
        },
        async addSubmit() {
            let isEmpty = this.$infoRule(this.addform)
            if (isEmpty == true) {
                this.$message.error("分类信息不完整!")
                return;
            }
            let { data: res } = await addCategory(this.addform)
            if (res.success) {
                this.$message.success("添加成功!")
                this.tableData = res.result
                this.addFormVisible = false
                this.$refs.upload1.clearFiles();
            } else {
                this.$message.error(res.msg)
            }
        },
        addNotice() {
            this.addform.categoryName = ''
            this.addform.categoryDesc = ''
            this.addform.categoryImage = ''
            this.addFormVisible = true
        },
        async search() {
            if (this.categoryName == '') {
                let { data: res } = await getAllCategory()
                this.tableData = res.result
            } else {
                let { data: res } = await searchCategory(this.categoryName)
                if (res.success) {
                    this.tableData = res.result
                    this.$message.success("搜索成功!")
                } else {
                    this.$message.error(res.msg)
                }
            }
        },
        handleRemove(file, fileList) {
            this.addform.categoryImage = ''
        },
        setUrl(response, file, fileList) {
            this.addform.categoryImage = response
        },
        handlePictureCardPreview(file) {
            this.addform.categoryImage = file;
            this.dialogVisible = true;
        },
        handleRemove2(file, fileList) {
            this.form.categoryImage = ''
        },
        setUrl2(response, file, fileList) {
            this.form.categoryImage = response
        },
        handlePictureCardPreview2(file) {
            this.form.categoryImage = file;
            this.dialogVisible2 = true;
        }
    },
    async created() {
        let { data: res } = await getAllCategory()
        this.tableData = res.result
    }
};
</script>

<style lang="less" scoped></style>