const { Router } = require('express')
const SubstitutionController = require('../controllers/substitution.controller')

const router = Router()

router.get('/', SubstitutionController.find)
router.get('/:id', SubstitutionController.findById)
router.get('/match/:match', SubstitutionController.findByMatch)
router.get('/team/:team', SubstitutionController.findByTeam)
router.get('/match/:match/team/:team', SubstitutionController.findByMatchAndTeam)

router.post('/', SubstitutionController.create)
router.post('/updateEverything', SubstitutionController.createAndUpdateEverything)

router.put('/:id', SubstitutionController.update)

router.delete('/:id', SubstitutionController.delete)

module.exports = router
