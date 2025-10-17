import request from '@/api/request.js'

export const getOrderList = function (currentPage, pageSize) {
    return request.get('/order/getOrderList/' + currentPage + '/' + pageSize, {
    })
}


export const addOrder = function (orderInfo) {
    return request({
        url: '/order/addOrder',
        method: 'POST',
        data: orderInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

export const updateOrderFlag = function (orderId, flag) {
    return request.get('/order/updateOrderFlag/' + orderId + '/' + flag, {
    })
}

export const deleteOrder = function (orderId) {
    return request.get('/order/deleteOrder/' + orderId, {
    })
}

export const searchOrder = function (flag) {
    return request.get('/order/searchOrder/' + flag, {
    })
}

export const userAddOrder = function (orderInfo) {
    return request({
        url: '/order/userAddOrder',
        method: 'POST',
        data: orderInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

export const userUpdateOrderFlag = function (orderId, flag) {
    return request.get('/order/userUpdateOrderFlag/' + orderId + '/' + flag, {
    })
}

export const getMyOrder = function (userId) {
    return request.get('/order/getMyOrder/' + userId, {
    })
}