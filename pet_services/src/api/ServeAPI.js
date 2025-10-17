import request from '@/api/request.js'

export const getServeList = function (currentPage, pageSize) {
    return request.get('/serve/getServeList/' + currentPage + '/' + pageSize, {
    })
}

//添加用户信息
export const addServe = function (serveInfo) {
    return request({
        url: '/serve/addServe',
        method: 'POST',
        data: serveInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

//修改用户信息
export const updateServe = function (serveInfo) {
    return request({
        url: '/serve/updateServe',
        method: 'POST',
        data: serveInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

//删除用户
export const deleteServe = function (serveId) {
    return request.get('/serve/deleteServe/' + serveId, {
    })
}

export const searchServe = function (serveName, category) {
    return request.post('/serve/searchServe', {
        serveName, category
    })
}

export const getAllServe = function () {
    return request.get('/serve/getAllServe', {
    })
}