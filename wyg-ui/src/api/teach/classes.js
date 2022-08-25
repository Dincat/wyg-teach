import request from '@/utils/request'

// 查询班级信息列表
export function listClasses(query) {
  return request({
    url: '/teach/classes/list',
    method: 'get',
    params: query
  })
}

// 查询班级信息详细
export function getClasses(id) {
  return request({
    url: '/teach/classes/' + id,
    method: 'get'
  })
}

// 新增班级信息
export function addClasses(data) {
  return request({
    url: '/teach/classes',
    method: 'post',
    data: data
  })
}

// 修改班级信息
export function updateClasses(data) {
  return request({
    url: '/teach/classes',
    method: 'put',
    data: data
  })
}

// 删除班级信息
export function delClasses(id) {
  return request({
    url: '/teach/classes/' + id,
    method: 'delete'
  })
}
