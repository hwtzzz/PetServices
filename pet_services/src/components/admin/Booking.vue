<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/" style="height: 35px;" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="'/adminHome'">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ this.$route.meta.title }}</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 搜索内容 -->
        <el-select v-model="flag" clearable placeholder="服务状态" style="width: 200px;">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <el-button type="primary" icon="el-icon-search" @click="search" style="margin-left: 10px;">搜索</el-button>

        <!-- excel导出 -->
        <el-button type="primary" style="margin-left: 20px;" @click="exportExcelSelect"
            icon="el-icon-folder-add">导出Excel文件</el-button>
        <!--excel导出预览弹窗表格-->
        <el-dialog title="表格保存预览" width="70%" :visible.sync="selectWindow">
            <el-table :data="selectData" id="selectTable" height="380px">
                <el-table-column prop="bookingId" label="预约编号">
                </el-table-column>
                <el-table-column prop="bookingDate" label="预约时间">
                </el-table-column>
                <el-table-column prop="name" label="预约人">
                </el-table-column>
                <el-table-column prop="phone" label="电话">
                </el-table-column>
                <el-table-column prop="serveName" label="服务项目">
                </el-table-column>
                <el-table-column prop="flag" label="服务状态">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.flag == 1">预约中</el-tag>
                        <el-tag v-if="scope.row.flag == 2">已服务</el-tag>
                        <el-tag v-if="scope.row.flag == 3">已评价</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="star" label="评分">
                </el-table-column>
                <el-table-column prop="appraise" label="服务评价">
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
            <el-table-column prop="bookingId" label="预约编号">
            </el-table-column>
            <el-table-column prop="bookingDate" label="预约时间" width="200">
            </el-table-column>
            <el-table-column prop="name" label="姓名">
            </el-table-column>
            <el-table-column prop="phone" label="电话">
            </el-table-column>
            <el-table-column prop="serveName" label="服务名称">
            </el-table-column>
            <el-table-column prop="flag" label="服务状态">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.flag == 1">预约中</el-tag>
                    <el-tag v-if="scope.row.flag == 2">已服务</el-tag>
                    <el-tag type="success" v-if="scope.row.flag == 3">已评价</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="star" label="评分"  width="200">
                <template slot-scope="scope">
                    <span v-if="scope.row.flag != 3">未评分</span>
                    <el-rate v-model="scope.row.star" show-text disabled v-if="scope.row.flag == 3">
                    </el-rate>
                </template>
            </el-table-column>
            <el-table-column prop="appraise" label="服务评价">
                <template slot-scope="scope">
                    <span v-if="scope.row.flag != 3">未评价</span>
                    <span v-if="scope.row.flag == 3">{{ scope.row.appraise }}</span>
                </template>
            </el-table-column>
            <!-- 操作 -->
            <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                    <!-- 修改状态为已服务 -->
                    <el-button type="primary" size="mini" @click="updateSubmit(scope.row.bookingId, 2)"
                        icon="el-icon-check" v-if="scope.row.flag == 1">点击完成服务</el-button>
                    <!-- 修改状态为已服务 -->
                    <el-tag v-if="scope.row.flag == 2">已服务,待评价</el-tag>
                    <el-tag type="success" v-if="scope.row.flag == 3">用户已评价</el-tag>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-row>
            <el-col style="text-align:left;margin-top: 10px;">
                <el-pagination @current-change="handleCurrentChange" :current-page="currentpage" :page-size="pagesize"
                    layout="total, prev, pager, next" :total="total">
                </el-pagination>
            </el-col>
        </el-row>

    </div>
</template>

<script>
import { getBookingList, deleteBooking, updateBookingFlag, searchBooking } from '@/api/BookingAPI.js'
import htmlToExcel from '@/utils/htmlToExcel.js'
export default {
    name: 'InnovationSystemBooking',

    data() {
        return {
            //表格中选中的数据
            selectData: [],
            selectWindow: false,
            tableData: [],
            options: [
                {
                    value: 1,
                    label: '预约中'
                },
                {
                    value: 2,
                    label: '已服务'
                },
                {
                    value: 3,
                    label: '已评价'
                }],
            flag: '',
            total: 0,  //总数据条数
            currentpage: 1,  //当前所在页默认是第一页
            pagesize: 8,  //每页显示多少行数据 默认设置为10
            addFormVisible: false,
            visible: false,
            dialogFormVisible: false,
            formLabelWidth: '120px',
            dialogVisible: false,
            dialogVisible2: false,
            form: {
                bookingId: '',
                bookingDate: '',
                userId: '',
                serve: '',
                appraise: '',
                star: '',
                flag: ''
            },
            updateVisible: false,
        };
    },

    mounted() {

    },

    methods: {
        async updateSubmit(bookingId, flag) {
            let { data: res } = await updateBookingFlag(bookingId, flag)
            if (res.success) {
                this.tableData = res.result.records
                this.total = res.result.total
            } else {
                this.$message.error(res.msg)
            }
        },
        quxiao() {
            this.addFormVisible = false
            this.updateVisible = false
        },
        //导出
        exportExcel() {
            htmlToExcel.getExcel('#selectTable', '服务预约信息')
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
        async handleCurrentChange(currentPage) {
            let { data: res } = await getBookingList(currentPage, 8)
            this.tableData = res.result.records
            this.total = res.result.total
        },
        async handleConfirm(row) {
            let { data: res } = await deleteBooking(row.bookingId)
            if (res.success) {
                this.$message.success("删除预约成功")
                this.tableData = res.result.records
                this.total = res.result.total
                this.visible = false
            } else {
                this.$message.error(res.msg)
            }
        },
        async search() {
            if (this.flag == '') {
                let { data: res } = await getBookingList(1, 8)
                this.tableData = res.result.records
                this.total = res.result.total
            } else {
                let { data: res } = await searchBooking(this.flag)
                if (res.success) {
                    this.tableData = res.result
                    this.total = res.result.length
                    this.$message.success("搜索成功!")
                } else {
                    this.$message.error(res.msg)
                }
            }
        },
    },
    async created() {
        let { data: res } = await getBookingList(1, 8)
        this.tableData = res.result.records
        this.total = res.result.total
    }
};
</script>

<style lang="less" scoped></style>