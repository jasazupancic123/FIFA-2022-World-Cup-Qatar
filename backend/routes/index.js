const { Router } = require('express')
const router = Router()

router.use('/team', require('./team.router'))
router.use('/player', require('./player.router'))
router.use('/club', require('./club.router'))
router.use('/match', require('./match.router'))
router.use('/goal', require('./goal.router'))
router.use('/lineup', require('./lineup.router'))
router.use('/substitution', require('./substitution.router'))
router.use('/manager', require('./manager.router'))

module.exports = router
