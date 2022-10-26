const { Router } = require('express')
const ClubController = require('../controllers/club.controller')

const router = Router()

router.get('/', ClubController.find)
router.get('/:id', ClubController.findById)
router.get('/name/:name', ClubController.findByName)
router.get('/league/:league', ClubController.findByLeague)

router.post('/', ClubController.create)

router.put('/:id', ClubController.update)

router.delete('/:id', ClubController.delete)

module.exports = router
