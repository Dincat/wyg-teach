import request from '@/utils/request'

// 查询学生信息列表
export function listStudent(query) {
  return request({
    url: '/teach/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生信息详细
export function getStudent(id) {
  return request({
    url: '/teach/student/' + id,
    method: 'get'
  })
}

// 查询学生信息详细
export function getStudentByIdNumber(idNumber) {
  return request({
    url: '/teach/student/getInfoByIdNumber/' + idNumber,
    method: 'get'
  })
}

// 新增学生信息
export function addStudent(data) {
  return request({
    url: '/teach/student',
    method: 'post',
    data: data
  })
}

// 修改学生信息
export function updateStudent(data) {
  return request({
    url: '/teach/student',
    method: 'put',
    data: data
  })
}

// 删除学生信息
export function delStudent(id) {
  return request({
    url: '/teach/student/' + id,
    method: 'delete'
  })
}
