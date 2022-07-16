/*
MIT License

Copyright (c) 2020 www.joolun.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
import request from '@/utils/request'

export function getPage(query) {
  return request({
    url: '/wechat/wxmaterial/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/wechat/wxmaterial/materialNews',
    method: 'post',
    data: obj
  })
}

export function materialNewsUpdate(obj) {
  return request({
    url: '/wechat/wxmaterial/materialNews',
    method: 'put',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/wechat/wxmaterial/' + id,
    method: 'get'
  })
}

export function delObj(query) {
  return request({
    url: '/wechat/wxmaterial',
    method: 'delete',
    params: query
  })
}

export function putObj(obj) {
  return request({
    url: '/wechat/wxmaterial',
    method: 'put',
    data: obj
  })
}

export function getMaterialOther(query) {
  return request({
    url: '/wechat/wxmaterial/materialOther',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

export function getMaterialVideo(query) {
  return request({
    url: '/wechat/wxmaterial/materialVideo',
    method: 'get',
    params: query
  })
}

export function  getTempMaterialOther(query) {
  return request({
    url: '/wechat/wxmaterial/tempMaterialOther',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
