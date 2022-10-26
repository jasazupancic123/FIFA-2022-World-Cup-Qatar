module.exports = class JsonUtil {
  static response(res, isError = false, message = '', data = {}) {
    return res.json({
      error: isError,
      message,
      data,
    })
  }
}
