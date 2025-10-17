import request from '@/api/request.js'

export const getGoodsList = function (currentPage, pageSize) {
    return request.get('/goods/getGoodsList/' + currentPage + '/' + pageSize, {
    })
}

//添加用户信息
export const addGoods = function (goodsInfo) {
    return request({
        url: '/goods/addGoods',
        method: 'POST',
        data: goodsInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

//修改用户信息
export const updateGoods = function (goodsInfo) {
    return request({
        url: '/goods/updateGoods',
        method: 'POST',
        data: goodsInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

//删除用户
export const deleteGoods = function (goodsId) {
    return request.get('/goods/deleteGoods/' + goodsId, {
    })
}

export const searchGoods = function (goodsName) {
    return request.get('/goods/searchGoods/' + goodsName, {
    })
}

export const getAllGoods = function () {
    return request.get('/goods/getAllGoods', {
    })
}