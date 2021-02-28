package com.example.p4.data

import com.example.p4.data.network.model.FamilyMemberModel
import com.example.p4.data.network.model.LastVisitModel
import com.example.p4.data.network.model.ProcedureModel
import com.example.p4.data.network.model.UserModel

class DummyData private constructor() {

    companion object {
        @get:Synchronized
        var instance: DummyData? = null
            get() {
                if (field == null) {
                    field = DummyData()
                }
                return field
            }
            private set
    }

    fun createDummyUserName(email: String): UserModel {
        val userData = UserModel()
        userData.userID = 1
        userData.userName = email.split("@").get(0)
        userData.email = email
        userData.avatar = "https://st3.depositphotos.com/3369547/12852/v/950/depositphotos_128525738-stock-illustration-woman-female-avatar-isolated.jpg"
        return userData
    }

    fun createDummyFamilyMembers(): ArrayList<FamilyMemberModel> {
        val familyMemebersData = ArrayList<FamilyMemberModel>()
        familyMemebersData.add(createDummyFamilyMember("Amy", 68, "https://i2.wp.com/nofiredrills.com/wp-content/uploads/2016/10/myavatar.png?fit=400%2C400&ssl=1", 1))
        familyMemebersData.add(createDummyFamilyMember("David", 24, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1HWDB6J1D4N-80QGiBJ8hgqVq3fFkSkcOTQ&usqp=CAU", 2))
        familyMemebersData.add(createDummyFamilyMember("Kim", 88, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHGqKC8WXE9zYm0uAbibq_YT2Y9DjUzXxlDg&usqp=CAU", 3))
        familyMemebersData.add(createDummyFamilyMember("Mathew", 74, "https://thumbs.dreamstime.com/b/portrait-young-man-beard-hair-style-male-avatar-vector-portrait-young-man-beard-hair-style-male-avatar-105082137.jpg", 4))

        return familyMemebersData
    }

    fun createDummyFamilyMember(familyMemberName: String, familyMemberScore: Int, familyMemberImageURL: String, procedureID: Int) : FamilyMemberModel{

        val procedureData = createDummyProcedure(procedureID)
        val proceduresData = ArrayList<ProcedureModel>()
        proceduresData.add(procedureData)

        val familyMemeberData = FamilyMemberModel()
        familyMemeberData.name = familyMemberName
        familyMemeberData.score = familyMemberScore
        familyMemeberData.avatarURL = familyMemberImageURL
        familyMemeberData.nextProcedure = proceduresData

        return familyMemeberData
    }

    fun createDummyProcedure(procedureID: Int) : ProcedureModel {

        val procedureData = ProcedureModel()
        procedureData.procedureID = procedureID

        when (procedureID) {
            1 -> {
                procedureData.name = "Mammogram"
                procedureData.value = 12
                procedureData.prevent = "Breast cancer"
                procedureData.probability = 1
                procedureData.probabilityScale = 8
                procedureData.lifePotential = 12
                procedureData.lifePotentialGoal = 82
                procedureData.lifePotentialGained = 20
                procedureData.personalMessage = "Hey Amy, It's been a year, let's book it!"
                procedureData.riskFactorList = listOf("Normal BRCA", "No family history", "85% of woman diagnosed don't have a family history")
                procedureData.lastVisit = createDummyLastVisit(procedureID)
            }

            2 -> {
                procedureData.name = "Yearly wellness"
                procedureData.value = 10
                procedureData.prevent = "Alzheimer's disease"
                procedureData.probability = 1
                procedureData.probabilityScale = 10
                procedureData.lifePotential = 25
                procedureData.lifePotentialGoal = 88
                procedureData.lifePotentialGained = 50
                procedureData.personalMessage = "Hey David, It's been a year, let's book it!"
                procedureData.riskFactorList = listOf("Diabetes", "Hypertension", "Heart disease", "Current smoking")
                procedureData.lastVisit = createDummyLastVisit(procedureID)
            }

            3 -> {
                procedureData.name = "Developmental screening"
                procedureData.value = 8
                procedureData.prevent = "Find out if more developmental evaluation are recommended"
                procedureData.probability = 3
                procedureData.probabilityScale = 9
                procedureData.lifePotential = 8
                procedureData.lifePotentialGoal = 65
                procedureData.lifePotentialGained = 40
                procedureData.personalMessage = "Hey Kim, It's been a year, let's book it!"
                procedureData.riskFactorList = listOf("Preterm birth", "Low birthweight", "Lead exposure")
                procedureData.lastVisit = createDummyLastVisit(procedureID)
            }

            4 -> {
                procedureData.name = "PSA"
                procedureData.value = 6
                procedureData.prevent = "Prostate cancer"
                procedureData.probability = 2
                procedureData.probabilityScale = 7
                procedureData.lifePotential = 6
                procedureData.lifePotentialGoal = 75
                procedureData.lifePotentialGained = 30
                procedureData.personalMessage = "Hey Mathew, It's been a year, let's book it!"
                procedureData.riskFactorList = listOf("Family history", "Race", "Aging and oxidative stress")
                procedureData.lastVisit = createDummyLastVisit(procedureID)
            }
        }

        return  procedureData
    }

    fun createDummyLastVisit(procedureID: Int) : LastVisitModel {

        val lastVisitData = LastVisitModel()

        val lastVisitsData = ArrayList<LastVisitModel>()
        lastVisitsData.add(lastVisitData)

        when (procedureID) {
            1 -> {
                lastVisitData.doctorName = "Dr. Shenav"
                lastVisitData.visitDateTime = "2019-09-01T14:58:25.738Z"
                lastVisitData.rate = 4f
            }

            2 -> {
                lastVisitData.doctorName = "Dr. Fisher"
                lastVisitData.visitDateTime = "2020-02-01T14:58:25.738Z"
                lastVisitData.rate = 2.5f
            }

            3 -> {
                lastVisitData.doctorName = "Prof. Miller"
                lastVisitData.visitDateTime = "2020-01-11T14:58:25.738Z"
                lastVisitData.rate = 3f
            }

            4 -> {
                lastVisitData.doctorName = "Dr. Rozen"
                lastVisitData.visitDateTime = "2020-05-3T14:58:25.738Z"
                lastVisitData.rate = 4f
            }
        }

        return  lastVisitData
    }
}