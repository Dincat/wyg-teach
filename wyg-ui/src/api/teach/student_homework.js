import request from '@/utils/request'

// 查询学生作业列表
export function listStudent_homework(query) {
  return request({
    url: '/teach/student/homework/list',
    method: 'get',
    params: query
  })
}

// 查询学生作业详细
export function getStudent_homework(id) {
  return request({
    url: '/teach/student/homework/' + id,
    method: 'get'
  })
}

// 新增学生作业
export function addStudent_homework(data) {
  return request({
    url: '/teach/student/homework',
    method: 'post',
    data: data
  })
}

// 修改学生作业
export function updateStudent_homework(data) {
  return request({
    url: '/teach/student/homework',
    method: 'put',
    data: data
  })
}

// 删除学生作业
export function delStudent_homework(id) {
  return request({
    url: '/teach/student/homework/' + id,
    method: 'delete'
  })
}
