<template>
    <div class="store-page">
        <!-- 搜索栏 -->
        <div class="search-bar">
            <el-input v-model="searchKeyword" placeholder="搜索服务名称" prefix-icon="el-icon-search" clearable />
            <el-select v-model="filterStatus" placeholder="全部" class="filter-select">
                <el-option label="全部" value="all" />
                <el-option v-for="item in categoryList" :key="item.categoryId" :label="item.categoryName"
                    :value="item.categoryId" />
            </el-select>
        </div>

        <div class="store-list">
            <el-row :gutter="20">
                <el-col v-for="serve in filteredStores" :key="serve.serveId" :xs="24" :sm="12" :md="8" :lg="6">
                    <div class="store-card">
                        <div class="store-image">
                            <img :src="serve.serveImage" alt="服务图片" />
                            <div class="status-tag available">
                                {{ getName(serve.category) }}
                            </div>
                        </div>
                        <div class="store-info">
                            <h3 class="store-name">{{ serve.serveName }}</h3>
                            <div class="store-address">描述：{{ serve.serveDesc }}</div>
                            <div style="display: flex;justify-content: space-between;align-items: center;">
                                <div class="store-price">价格：{{ serve.price + "￥" }}</div>
                                <div class="adoptBtn" @click="booking(serve.serveId)">预约</div>
                            </div>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>

        <!-- 预约框 -->
        <el-dialog title="预约信息" :visible.sync="dialogFormVisible">
            <el-form :model="bookingForm">
                <el-form-item label="预约时间" :label-width="formLabelWidth">
                    <el-date-picker v-model="bookingForm.bookingDate" type="date" :picker-options="pickerOptions"
                        format="yyyy-MM-dd" value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="bookingSubmit()">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import { getAllServe } from '@/api/ServeAPI.js';
import { getAllCategory } from '@/api/CategoryAPI.js'
import { addBooking } from '@/api/BookingAPI.js'
export default {
    name: 'SmartCommunityStores',

    data() {
        return {
            searchKeyword: '',
            filterStatus: 'all',
            serves: [], // 从后端获取的数据
            categoryList: [],
            bookingForm: {
                bookingDate: '',
                userId: '',
                serve: ''
            },
            dialogFormVisible: false,
            formLabelWidth: '120px',
            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() < Date.now();
                }
            },
        };
    },
    computed: {
        filteredStores() {
            return this.serves.filter(serve => {
                const keywordMatch =
                    serve.serveName?.includes(this.searchKeyword)
                const statusMatch =
                    this.filterStatus === 'all' ||
                    serve.category === this.filterStatus;
                return keywordMatch && statusMatch;
            });
        }
    },
    methods: {
        async bookingSubmit() {
            let isEmpty = this.$infoRule(this.bookingForm)
            if (isEmpty == true) {
                this.$message.error("信息不完整!")
                return;
            }
            let {data:res} = await addBooking(this.bookingForm)
            if (res.success) {
                this.dialogFormVisible = false
                this.$message.success("预约成功!")
            }else{
                this.$message.error(res.msg)
            }
            
        },
        booking(serveId) {
            this.bookingForm.serve = serveId
            this.bookingForm.userId = JSON.parse(localStorage.getItem("user")).userId
            this.bookingForm.bookingDate = ''
            this.dialogFormVisible = true
        },
        getName(category) {
            return this.categoryList.find(item => item.categoryId == category).categoryName
        },
        async applyAdopt(animalId) {
            let userId = JSON.parse(localStorage.getItem("user")).userId
            let { data: res } = await applyAdopt(animalId, userId);
            if (res.success) {
                this.$message.success("已提交领养申请!")
            } else {
                this.$message.error(res.msg)
            }
        }
    },
    async created() {
        let { data: res } = await getAllServe()
        this.serves = res.result
        let { data: r } = await getAllCategory()
        this.categoryList = r.result
    }
};
</script>
<style lang="less">
.adoptBtn {
    height: 30px;
    width: 80px;
    background-color: #6fbee6;
    border-radius: 20px;
    color: white;
    font-size: 14px;
    text-align: center;
    line-height: 30px;
    cursor: pointer;
}

.store-page {
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;

    .search-bar {
        display: flex;
        gap: 15px;
        margin-bottom: 30px;

        .el-input {
            flex: 1;
        }

        .filter-select {
            width: 150px;
        }
    }

    .store-list {
        .store-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            overflow: hidden;
            transition: transform 0.3s;

            &:hover {
                transform: translateY(-5px);
            }

            .store-image {
                position: relative;
                height: 200px;

                img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                }

                .status-tag {
                    position: absolute;
                    top: 10px;
                    right: 10px;
                    padding: 6px 12px;
                    border-radius: 20px;
                    font-size: 12px;
                    color: white;

                    &.available {
                        background: #67C23A;
                    }

                    &.sold {
                        background: #F56C6C;
                    }
                }
            }

            .store-info {
                padding: 15px;

                .store-name {
                    font-size: 18px;
                    margin: 0 0 10px;
                    color: #303133;
                }

                .store-price {
                    color: #E6A23C;
                    font-weight: bold;
                    margin: 8px 0;
                }

                .store-address {
                    height: 60px;
                    color: #606266;
                    font-size: 14px;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 3;
                    /*显示几行*/
                    overflow: hidden;
                    text-overflow: ellipsis;
                }
            }
        }
    }
}
</style>