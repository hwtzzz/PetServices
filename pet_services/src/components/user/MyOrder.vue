<template>
    <div>

        <div class="myBooking">我的订单信息</div>

        <!-- 数据列表 -->
        <el-table :data="tableData" style="width: 100%;margin-top: 10px" border stripe>
            <el-table-column prop="orderId" label="订单编号">
            </el-table-column>
            <el-table-column prop="goodsName" label="商品名称">
            </el-table-column>
            <el-table-column prop="count" label="商品数量">
            </el-table-column>
            <el-table-column prop="totalPrice" label="总金额">
            </el-table-column>
            <el-table-column prop="orderTime" label="下单时间" width="200">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column prop="flag" label="服务状态">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.flag == 1">已下单</el-tag>
                    <el-tag v-if="scope.row.flag == 2">送货中</el-tag>
                    <el-tag type="success" v-if="scope.row.flag == 3">已收货</el-tag>
                </template>
            </el-table-column>
            <!-- 操作 -->
            <el-table-column label="操作" width="300">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.flag == 1">已下单，等待商家配送</el-tag>
                    <!-- 修改状态为已服务 -->
                    <el-button type="primary" size="mini" @click="updateSubmit(scope.row.orderId, 3)"
                        icon="el-icon-check" v-if="scope.row.flag == 2">点击收货</el-button>
                        
                    <!-- 修改状态为已服务 -->
                    <el-tag type="success" v-if="scope.row.flag == 3">已收货</el-tag>

                </template>
            </el-table-column>
        </el-table>

    </div>
</template>

<script>
import { getMyOrder, userUpdateOrderFlag } from '@/api/OrderAPI.js'
export default {
    name: 'PetServicesMyBooking',

    data() {
        return {
            tableData: [],
        };
    },

    mounted() {

    },

    methods: {
        async updateSubmit(orderId, flag) {
            let { data: res } = await userUpdateOrderFlag(orderId, flag)
            if (res.success) {
                this.$message.success("已确认收货!")
                this.tableData = res.result
            } else {
                this.$message.error(res.msg)
            }
        },
    },
    async created() {
        let userId = JSON.parse(localStorage.getItem("user")).userId
        let { data: res } = await getMyOrder(userId)
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