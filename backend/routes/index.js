const { Router } = require('express')
const router = Router()

router.use('/country', require('./country.router'))

module.exports = router
