import request from '@/utils/request'

// 查询教职工信息列表
export function listTeacher(query) {
  return request({
    url: '/teach/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询教职工信息详细
export function getTeacher(id) {
  return request({
    url: '/teach/teacher/' + id,
    method: 'get'
  })
}

// 查询教职工信息详细
export function getTeacherByIdNumber(idNumber) {
  return request({
    url: '/teach/teacher/getInfoByIdNumber/' + idNumber,
    method: 'get'
  })
}

// 新增教职工信息
export function addTeacher(data) {
  return request({
    url: '/teach/teacher',
    method: 'post',
    data: data
  })
}

// 修改教职工信息
export function updateTeacher(data) {
  return request({
    url: '/teach/teacher',
    method: 'put',
    data: data
  })
}

// 删除教职工信息
export function delTeacher(id) {
  return request({
    url: '/teach/teacher/' + id,
    method: 'delete'
  })
}
