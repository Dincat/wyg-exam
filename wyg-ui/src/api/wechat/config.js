import request from '@/utils/request'

// 查询微信配置列表
export function listConfig(query) {
  return request({
    url: '/wechat/config/list',
    method: 'get',
    params: query
  })
}

// 查询微信配置详细
export function getConfig(id) {
  return request({
    url: '/wechat/config/' + id,
    method: 'get'
  })
}

// 新增微信配置
export function addConfig(data) {
  return request({
    url: '/wechat/config',
    method: 'post',
    data: data
  })
}

// 修改微信配置
export function updateConfig(data) {
  return request({
    url: '/wechat/config',
    method: 'put',
    data: data
  })
}

// 删除微信配置
export function delConfig(id) {
  return request({
    url: '/wechat/config/' + id,
    method: 'delete'
  })
}
