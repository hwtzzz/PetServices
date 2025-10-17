<template>
    <el-container class="admin-layout">
        <!-- 头部 -->
        <el-header class="header">
            <div class="header-content">
                <div class="logo">
                    <span class="title">🐾 宠物服务预约管理系统</span>
                </div>
                <el-dropdown trigger="click" @command="handleCommand">
                    <div class="user-info">
                        <el-avatar :src="user.avatar"></el-avatar>
                        <span class="username">{{ user.username }}</span>
                        <i class="el-icon-arrow-down"></i>
                    </div>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="logout">
                            <i class="el-icon-switch-button"></i>退出登录
                        </el-dropdown-item>
                        <el-dropdown-item command="changePassword">
                            <i class="el-icon-refresh-right"></i>更改密码
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-header>

        <el-container>
            <!-- 侧边导航栏 -->
            <el-aside width="200px" class="sidebar">
                <el-menu router class="nav-menu" background-color="#f4f7fa" text-color="#333"
                    active-text-color="#409EFF" :default-active="$route.path">
                    <el-menu-item index="/adminHome/welcome">
                        <i class="el-icon-menu"></i>
                        <span>首页</span>
                    </el-menu-item>

                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-user-solid"></i>
                            <span>人员管理</span>
                        </template>
                        <el-menu-item index="/adminHome/user">
                            <i class="el-icon-user"></i>
                            <span>用户管理</span>
                        </el-menu-item>
                        <el-menu-item index="/adminHome/admin">
                            <i class="el-icon-user"></i>
                            <span>管理员管理</span>
                        </el-menu-item>
                    </el-submenu>
                    <el-menu-item index="/adminHome/category">
                        <i class="el-icon-s-grid"></i>
                        <span>服务分类管理</span>
                    </el-menu-item>
                    <el-menu-item index="/adminHome/serve">
                        <i class="el-icon-s-cooperation"></i>
                        <span>服务管理</span>
                    </el-menu-item>
                    <el-menu-item index="/adminHome/goods">
                        <i class="el-icon-s-goods"></i>
                        <span>商品管理</span>
                    </el-menu-item>
                    <el-menu-item index="/adminHome/booking">
                        <i class="el-icon-phone"></i>
                        <span>预约管理</span>
                    </el-menu-item>
                    <el-menu-item index="/adminHome/order">
                        <i class="el-icon-s-order"></i>
                        <span>订单管理</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>

            <!-- 主内容区 -->
            <el-main class="main-content">
                <el-card class="content-card">
                    <router-view></router-view>
                </el-card>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {
    data() {
        return {
            user: ''
        };
    },
    methods: {
        handleCommand(command) {
            if (command === 'logout') {
                this.$confirm('确定要退出登录吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // 执行退出登录操作
                    this.$router.push('/login')
                    localStorage.removeItem("admin")
                })
            }
            if (command === 'changePassword') {
                this.$router.push('/adminHome/changePassword')
            }
        }
    },
    async created() {
        this.user = JSON.parse(localStorage.getItem("admin"))
    }
}
</script>

<style lang="less" scoped>
.admin-layout {
    height: 100vh;

    .header {
        background: linear-gradient(135deg, #6B8DD6 0%, #8E44AD 100%);
        padding: 0 20px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

        .header-content {
            height: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;

            .logo {
                .title {
                    color: white;
                    font-size: 20px;
                    font-weight: bold;
                    letter-spacing: 1px;
                }
            }

            .user-info {
                display: flex;
                align-items: center;
                color: white;
                cursor: pointer;

                .el-avatar {
                    margin-right: 10px;
                    background-color: rgba(255, 255, 255, 0.3);
                }

                .username {
                    margin-right: 8px;
                }
            }
        }
    }

    .sidebar {
        background-color: #f4f7fa;
        transition: width 0.3s ease;

        .nav-menu {
            border-right: none;
            transition: all 0.3s;

            .el-menu-item {
                transition: all 0.2s;

                &:hover {
                    background-color: #e8f4ff !important;
                }
            }
        }
    }

    .main-content {
        background-color: #f0f2f5;
        padding: 20px;

        .content-card {
            min-height: calc(100vh - 100px);
            border-radius: 8px;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        }
    }
}

.el-dropdown-menu__item {
    padding: 12px 20px;
    transition: all 0.2s;

    i {
        margin-right: 8px;
    }

    &:hover {
        background-color: #f5f7fa;
        color: #6B8DD6;
    }
}
</style>