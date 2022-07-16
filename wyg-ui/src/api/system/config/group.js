import request from '@/utils/request'

// 查询参数分组列表
export function listGroup(query) {
  return request({
    url: '/system/config/group/list',
    method: 'get',
    params: query
  })
}

// 查询参数分组详细
export function getGroup(id) {
  return request({
    url: '/system/config/group/' + id,
    method: 'get'
  })
}

// 新增参数分组
export function addGroup(data) {
  return request({
    url: '/system/config/group',
    method: 'post',
    data: data
  })
}

// 修改参数分组
export function updateGroup(data) {
  return request({
    url: '/system/config/group',
    method: 'put',
    data: data
  })
}

// 删除参数分组
export function delGroup(id) {
  return request({
    url: '/system/config/group/' + id,
    method: 'delete'
  })
}
