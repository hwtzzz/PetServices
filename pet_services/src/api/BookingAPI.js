import request from '@/api/request.js'

export const getBookingList = function (currentPage, pageSize) {
    return request.get('/booking/getBookingList/' + currentPage + '/' + pageSize, {
    })
}


export const addBooking = function (bookingInfo) {
    return request({
        url: '/booking/addBooking',
        method: 'POST',
        data: bookingInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}

export const updateBookingFlag = function (bookingId, flag) {
    return request.get('/booking/updateBookingFlag/' + bookingId + '/' + flag, {
    })
}

export const deleteBooking = function (bookingId) {
    return request.get('/booking/deleteBooking/' + bookingId, {
    })
}

export const searchBooking = function (flag) {
    return request.get('/booking/searchBooking/' + flag, {
    })
}

export const getMyBooking = function (userId) {
    return request.get('/booking/getMyBooking/' + userId, {
    })
}

export const updateBookingComment = function (bookingInfo) {
    return request({
        url: '/booking/updateBookingComment',
        method: 'POST',
        data: bookingInfo,
        header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
    })
}