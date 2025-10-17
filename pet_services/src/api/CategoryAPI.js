import request from '@/api/request.js'

//获取所有用户信息
export const getAllCategory = function () {
  return request.get('/category/getCategoryList', {
  })
}

//添加用户信息
export const addCategory = function (categoryInfo) {
  return request({
    url: '/category/addCategory',
    method: 'POST',
    data: categoryInfo,
    header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
  })
}

//修改用户信息
export const updateCategory = function (categoryInfo) {
  return request({
    url: '/category/updateCategory',
    method: 'POST',
    data: categoryInfo,
    header: { 'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN' }
  })
}

//删除用户
export const deleteCategory = function (categoryId) {
  return request.get('/category/deleteCategory/' + categoryId, {
  })
}

export const searchCategory = function (categoryName) {
  return request.get('/category/searchCategory/' + categoryName, {
    
  })
}