<template>
    <div>
        <!-- Hero 部分 -->
        <div class="hero">
            <div class="hero-content">
                <h1 class="hero-title">欢迎来到萌宠广场</h1>
                <p class="hero-description">在这里，您可以预约各种宠物服务，享受与萌宠的亲密时光。</p>
            </div>
        </div>

        <!-- 服务信息部分 -->
        <div class="services">
            <h2 class="services-title">我们的服务</h2>
            <div class="service-cards">
                <div v-for="(service, index) in services" :key="index" class="service-card"
                    :style="{ animationDelay: `${index * 0.2}s` }">
                    <img :src="service.serveImage" alt="服务图片" class="service-image" />
                    <h3 class="service-name">{{ service.serveName }}</h3>
                    <p class="service-description">{{ service.serveDesc }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { getAllServe } from "@/api/ServeAPI.js";
export default {
    name: 'PetSquare',
    data() {
        return {
            services: [
            ],
        };
    },
    async created() {
        let { data: res } = await getAllServe()
        this.services = res.result
    }
};
</script>

<style scoped>
/* Hero 部分样式 */
.hero {
    background-image: url('@/assets/keji.png');
    background-size: cover;
    background-position: center;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: #fff;
    position: relative;
    overflow: hidden;
}

.hero::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    z-index: 1;
}

.hero-content {
    z-index: 2;
    animation: fadeIn 2s ease-in-out;
}

.hero-title {
    font-size: 3rem;
    font-weight: bold;
    margin-bottom: 1rem;
    animation: slideInFromLeft 1s ease-out;
}

.hero-description {
    font-size: 1.5rem;
    animation: slideInFromRight 1s ease-out;
}

/* 服务信息部分样式 */
.services {
    padding: 2rem;
    background-color: #f9f9f9;
}

.services-title {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 2rem;
}

.service-cards {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    gap: 1.5rem;
}

.service-card {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 30%;
    text-align: center;
    padding: 1rem;
    opacity: 0;
    transform: translateY(20px);
    animation: fadeInUp 0.5s forwards;
    position: relative;
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.service-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
}

.service-card::before {
    content: '';
    position: absolute;
    top: -5px;
    left: -5px;
    right: -5px;
    bottom: -5px;
    border: 2px solid transparent;
    background: linear-gradient(45deg, #ff6ec7, #ff9a8b, #ff6ec7, #ff9a8b);
    background-size: 400%;
    border-radius: 10px;
    z-index: -1;
    animation: borderAnimation 3s linear infinite;
}

.service-card:hover::before {
    background-position: 100% 100%;
}

.service-image {
    width: 100%;
    border-radius: 8px;
    margin-bottom: 1rem;
}

.service-name {
    font-size: 1.25rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
}

.service-description {
    font-size: 1rem;
    color: #666;
}

/* 动画效果 */
@keyframes fadeIn {
    0% {
        opacity: 0;
    }

    100% {
        opacity: 1;
    }
}

@keyframes slideInFromLeft {
    0% {
        transform: translateX(-100%);
    }

    100% {
        transform: translateX(0);
    }
}

@keyframes slideInFromRight {
    0% {
        transform: translateX(100%);
    }

    100% {
        transform: translateX(0);
    }
}

@keyframes fadeInUp {
    0% {
        opacity: 0;
        transform: translateY(20px);
    }

    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes borderAnimation {
    0% {
        background-position: 0% 0%;
    }

    25% {
        background-position: 100% 0%;
    }

    50% {
        background-position: 100% 100%;
    }

    75% {
        background-position: 0% 100%;
    }

    100% {
        background-position: 0% 0%;
    }
}
</style>