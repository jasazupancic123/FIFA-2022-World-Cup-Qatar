const { Router } = require('express')
const ManagerController = require('../controllers/manager.controller')

const router = Router()

router.get('/', ManagerController.find)
router.get('/:id', ManagerController.findById)

router.post('/', ManagerController.create)

router.put('/:id', ManagerController.update)

router.delete('/:id', ManagerController.delete)

module.exports = router
