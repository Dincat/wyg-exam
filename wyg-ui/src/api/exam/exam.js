import request from '@/utils/request'
import { apiList } from '@/const/constant'

export function fetchList (query) {
  return request({
    url: apiList.exam + 'list',
    method: 'get',
    params: query
  })
}

export function fetchSubjectListById (query) {
  return request({
    url: apiList.exam + 'subjectList',
    method: 'get',
    params: query
  })
}

export function getObj (id) {
  return request({
    url: apiList.exam + id,
    method: 'get'
  })
}


export function addObj (obj) {
  return request({
    url: apiList.exam,
    method: 'post',
    data: obj
  })
}

export function putObj (obj) {
  return request({
    url: apiList.exam,
    method: 'put',
    data: obj
  })
}

export function delObj (id) {
  return request({
    url: apiList.exam + id,
    method: 'delete'
  })
}

export function delAllObj (obj) {
  return request({
    url: apiList.exam + 'deleteAll',
    method: 'post',
    data: obj
  })
}

export function generateQrCode (id) {
  return request({
    url: apiList.exam + 'anonymousUser/generateQrCode/' + id,
    method: 'get'
  })
}


