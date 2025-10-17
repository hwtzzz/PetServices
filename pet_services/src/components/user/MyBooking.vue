<template>
    <div>

        <div class="myBooking">我的预约信息</div>

        <!-- 数据列表 -->
        <el-table :data="tableData" style="width: 90%;margin:0 auto;" border stripe>
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
            <el-table-column prop="star" label="评分">
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
                    <el-tag v-if="scope.row.flag == 1">等待服务</el-tag>

                    <el-button type="primary" size="mini" @click="updateOpen(scope.row.bookingId)"
                        icon="el-icon-s-comment" v-if="scope.row.flag == 2">评价本次服务</el-button>

                    <!-- 修改状态为已服务 -->
                    <el-tag type="success" v-if="scope.row.flag == 3">已完成评价</el-tag>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="评价本次服务吧" :visible.sync="updateVisible">
            <el-form :model="booking">
                <el-form-item label="评价" :label-width="formLabelWidth">
                    <el-input v-model="booking.appraise" autocomplete="off" type="textarea" :rows="5" maxlength="300"
                        show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="打分" :label-width="formLabelWidth">
                    <el-rate v-model="booking.star" style="margin-top: 10px;" show-text>
                    </el-rate>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="updateVisible = false">我再想想</el-button>
                <el-button type="primary" @click="updateSubmit()">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import { getMyBooking, updateBookingComment } from '@/api/BookingAPI.js'
export default {
    name: 'PetServicesMyBooking',

    data() {
        return {
            tableData: [],
            booking: {
                bookingId: '',
                appraise: '',
                star: 0
            },
            updateVisible: false,
            formLabelWidth: '120px',
        };
    },

    mounted() {

    },

    methods: {
        async updateSubmit() {
            let isEmpty = this.$infoRule(this.booking)
            if (isEmpty == true) {
                this.$message.error("评价信息不完整!")
                return;
            }
            let {data:res} = await updateBookingComment(this.booking)
            if (res.success) {
                this.$message.success("感谢您珍贵的评价!")
                this.tableData = res.result
                this.updateVisible = false
            } else {
                this.$message.error(res.msg)
            }
        },
        updateOpen(bookingId) {
            this.booking.bookingId = bookingId
            this.booking.appraise = ''
            this.booking.star = 0
            this.updateVisible = true
        }
    },
    async created() {
        let userId = JSON.parse(localStorage.getItem("user")).userId
        let { data: res } = await getMyBooking(userId)
        this.tableData = res.result
    }
};
</script>
<style scoped>
.myBooking {
    text-align: center;
    font-size: 40px;
    font-weight: 600;
    padding: 20px;
}
</style>