const { Router } = require('express')
const router = Router()

router.use('/country', require('./country.router'))
router.use('/player', require('./player.router'))
router.use('/club', require('./club.router'))
router.use('/match', require('./match.router'))
router.use('/goal', require('./goal.router'))
router.use('/lineup', require('./lineup.router'))

module.exports = router
