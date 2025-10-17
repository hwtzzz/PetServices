<template>
    <div>

        <div class="banner">
            <img class="bannerImage" :src="banner" alt="" srcset="">
            <div class="searchBox">
                <input type="text" placeholder="搜商品" v-model="goodsName">
                <button class="searchBtn" @click="searchGoods()">搜&nbsp;索</button>
            </div>
        </div>

        <div
            style="position: relative;width: 100%;height: 60px;font-size: 20px;font-weight: 600;line-height: 60px;text-align: center;">
            / 宠物商品专区
            / </div>

        <div class="goodsBigBox">
            <div v-for="i in goodsList" class="goodsBox">
                <div class="goodsImage">
                    <img :src="i.goodsImage" alt="" srcset="" :title="i.goodsName">
                </div>
                <div class="title">
                    <el-popover placement="top-start" :title="i.title" width="200" trigger="hover" :content="i.content">
                        <b style="color: brown;" slot="reference">{{ i.goodsName + ':' }}</b>
                    </el-popover>
                    {{ i.goodsDesc | ellipsis }}
                </div>
                <div class="price">
                    {{ '价格：' + i.price + "￥"}}
                </div>
                <div class="education">
                    <div>{{ '库存：' + i.stock }}</div>
                </div>
                <div class="buy" @click="buyGoods(i.goodsId, i.stock, i.price)">
                    购买商品
                </div>
            </div>
        </div>

        <!-- 购买商品的弹窗 -->
        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
            <el-form :model="order">
                <el-form-item label="商品数量" :label-width="formLabelWidth">
                    <el-input-number v-model="order.count" controls-position="right" @change="handleChange" :min="1"
                        :max="stock"></el-input-number>
                </el-form-item>
                <el-form-item label="总计金额" :label-width="formLabelWidth">
                    <el-input v-model="order.totalPrice" :disabled="true">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="buyGoodsSubmit()">确认购买</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import banner from '@/assets/banner.png'
import { getAllGoods, searchGoods } from "@/api/GoodsAPI.js";
import { userAddOrder } from "@/api/OrderAPI.js"
export default {
    name: 'PetServicesPetGoods',

    data() {
        return {
            banner: banner,
            goodsList: [],
            goodsName: '',
            stock: 0,
            price: '',
            order: {
                goodsId: '',
                userId: '',
                flag: 1,
                count: 1,
                totalPrice: 0
            },
            dialogFormVisible: false,
            formLabelWidth: '120px'
        };
    },

    mounted() {

    },
    filters: {
        ellipsis(value) {
            if (!value) return '';
            if (value.length > 90) {
                return value.slice(0, 90) + '...'
            }
            return value
        }
    },
    methods: {
        async buyGoodsSubmit() {
            let isEmpty = this.$infoRule(this.order)
            if (isEmpty == true) {
                this.$message.error("信息不完整!")
                return;
            }
            if (this.order.count == undefined) {
                this.$message.error("请选择数量!")
                return;
            }
            let {data:res} = await userAddOrder(this.order)
            if (res.success) {
                this.goodsList = res.result
                this.$message.success("已购买此商品，请等待商家配送!")
                this.dialogFormVisible = false
            }else{
                this.$message.error(res.msg)
            }
        },
        buyGoods(goodsId, stock, price) {
            this.stock = stock
            this.order.goodsId = goodsId
            this.price = price
            this.order.totalPrice = price
            this.order.userId = JSON.parse(localStorage.getItem("user")).userId
            this.dialogFormVisible = true
        },
        handleChange(value) {
            this.order.totalPrice = value * this.price
        },
        async searchGoods() {
            if (this.goodsName == '') {
                let { data: res } = await getAllGoods();
                this.goodsList = res.result
            } else {
                let { data: res } = await searchGoods(this.goodsName);
                this.goodsList = res.result
            }
        }
    },
    async created() {
        let { data: res } = await getAllGoods();
        this.goodsList = res.result
    },

};
</script>
<style scoped>
.el-tag {
    position: absolute;
}

.banner {
    position: relative;
    width: 1280px;
    height: 400px;
    margin: 20px auto;
}

.banner .bannerImage {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 30px;
}

.banner .titleImage {
    position: absolute;
    width: 500px;
    height: 150px;
    left: 31%;
    top: 150px;
}

.searchBox {
    width: 570px;
    height: 50px;
    position: absolute;
    left: 28%;
    top: 320px;
}

.searchBox input {
    height: 50px;
    width: 450px;
    outline: none;
    padding-left: 20px;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
    position: absolute;
}

.searchBtn {
    height: 100%;
    width: 100px;
    position: absolute;
    left: 470px;
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
    font-size: 20px;
    font-weight: 600;
    color: white;
    cursor: pointer;
    background-color: #00C9A7;
}

.goodsBigBox {
    position: relative;
    width: 1280px;
    display: flex;
    flex-flow: wrap;
    flex-direction: row;
    justify-content: space-around;
    margin: 0 auto;
}

.goodsBox {
    height: 350px;
    width: 237px;
    margin-top: 5px;
    margin-left: 5px;
    border: 1px solid rgb(169, 168, 168);
    position: relative;
}

.goodsImage {
    height: 150px;
    width: 150px;
    position: absolute;
    margin: 10px 44px;
}

.goodsImage img {
    width: 100%;
    height: 100%;
    cursor: pointer;
}

.goodsBox .title {
    width: 206px;
    height: 115px;
    font: 14px / 16px "Microsoft Yahei", tahoma, arial, "Hiragino Sans GB";
    position: absolute;
    top: 170px;
    left: 24px;
}

.address {
    width: 206px;
    height: 50px;
    position: absolute;
    top: 235px;
    left: 24px;
    font: 12px / 16px "Microsoft Yahei", tahoma, arial, "Hiragino Sans GB";
}

.education {
    width: 195px;
    height: 20px;
    position: absolute;
    top: 288px;
    left: 24px;
    font: 14px / 14px "Microsoft Yahei", tahoma, arial, "Hiragino Sans GB";
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    color: #00C9A7;
}

.price {
    width: 100px;
    height: 21px;
    position: absolute;
    left: 24px;
    top: 315px;
    color: orange;
    font: bold 16px / 16px "Microsoft Yahei", tahoma, arial, "Hiragino Sans GB";
}

.buy {
    width: 80px;
    height: 25px;
    position: absolute;
    left: 130px;
    top: 312px;
    background-color: #00C9A7;
    border-radius: 20px;
    text-align: center;
    line-height: 25px;
    cursor: pointer;
    color: aliceblue;
}
</style>