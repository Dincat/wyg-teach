import request from '@/utils/request'

// 查询学期阶段列表
export function listStage(query) {
  return request({
    url: '/teach/stage/list',
    method: 'get',
    params: query
  })
}

// 查询学期阶段详细
export function getStage(id) {
  return request({
    url: '/teach/stage/' + id,
    method: 'get'
  })
}

// 新增学期阶段
export function addStage(data) {
  return request({
    url: '/teach/stage',
    method: 'post',
    data: data
  })
}

// 修改学期阶段
export function updateStage(data) {
  return request({
    url: '/teach/stage',
    method: 'put',
    data: data
  })
}

// 删除学期阶段
export function delStage(id) {
  return request({
    url: '/teach/stage/' + id,
    method: 'delete'
  })
}
