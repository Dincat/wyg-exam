import request from '@/utils/request'
// 查询素材详细
export function getMaterialInfo(materialId) {
  return request({
    url: '/file/material/' + materialId,
    method: 'get'
  })
}


// 素材上传
export function uploadMaterial(data) {
  return request({
    url: '/file/material/upload',
    method: 'post',
    data: data
  })
}

// 查询素材信息列表
export function listMaterial(query) {
  return request({
    url: '/file/material/list',
    method: 'get',
    params: query
  })
}

// 修改素材信息
export function updateMaterial(data) {
  return request({
    url: '/file/material',
    method: 'put',
    data: data
  })
}

// 删除素材信息
export function delMaterial(materialId) {
  return request({
    url: '/file/material/' + materialId,
    method: 'delete'
  })
}

// 查询父级分类信息
export function parentFolder(query) {
  return request({
    url: '/file/folder/parent',
    method: 'get',
    params: query
  })
}

// 新增素材分类
export function addFolder(data) {
  return request({
    url: '/file/folder',
    method: 'post',
    data: data
  })
}

// 修改素材分类
export function updateFolder(data) {
  return request({
    url: '/file/folder',
    method: 'put',
    data: data
  })
}

// 删除素材分类
export function delFolder(folderId) {
  return request({
    url: '/file/folder/' + folderId,
    method: 'delete'
  })
}
