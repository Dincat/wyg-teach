import request from '@/utils/request'

// 查询专业课程列表
export function listCourse(query) {
  return request({
    url: '/teach/major/course/list',
    method: 'get',
    params: query
  })
}

// 查询专业课程详细
export function getCourse(id) {
  return request({
    url: '/teach/major/course/' + id,
    method: 'get'
  })
}

// 新增专业课程
export function addCourse(data) {
  return request({
    url: '/teach/major/course',
    method: 'post',
    data: data
  })
}

// 修改专业课程
export function updateCourse(data) {
  return request({
    url: '/teach/major/course',
    method: 'put',
    data: data
  })
}

// 删除专业课程
export function delCourse(id) {
  return request({
    url: '/teach/major/course/' + id,
    method: 'delete'
  })
}
