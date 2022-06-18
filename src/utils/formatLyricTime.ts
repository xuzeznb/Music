export function formatLyricTime(time: any) {
  const regMin = /.*:/
  const regSec = /:.*\./
  const regMs = /\./
  const min = parseInt(time.match(regMin)[0].slice(0, 2))
  let sec = parseInt(time.match(regSec)[0].slice(1, 3))
  const ms = time.slice(time.match(regMs).index + 1, time.match(regMs).index + 3)
  if (min !== 0) {
    sec += min * 60
  }
  return Number(sec + '.' + ms)
}
