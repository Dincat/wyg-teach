import request from '@/utils/request'

// 查询班级作业列表
export function listClass_homework(query) {
  return request({
    url: '/teach/class/homework/list',
    method: 'get',
    params: query
  })
}

// 查询班级作业详细
export function getClass_homework(id) {
  return request({
    url: '/teach/class/homework/' + id,
    method: 'get'
  })
}

// 新增班级作业
export function addClass_homework(data) {
  return request({
    url: '/teach/class/homework',
    method: 'post',
    data: data
  })
}

// 修改班级作业
export function updateClass_homework(data) {
  return request({
    url: '/teach/class/homework',
    method: 'put',
    data: data
  })
}

// 删除班级作业
export function delClass_homework(id) {
  return request({
    url: '/teach/class/homework/' + id,
    method: 'delete'
  })
}

export function publish(id) {
  return request({
    url: '/teach/class/homework/publish/' + id,
    method: 'get'
  })
}
