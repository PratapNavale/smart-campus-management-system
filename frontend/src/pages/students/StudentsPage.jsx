import { useEffect, useMemo, useState } from "react";

import {
  getStudents,
  registerStudent,
  updateStudent,
  deleteStudent,
} from "../../api/studentApi";

import StudentModal from "../../components/students/StudentModal";

function StudentsPage() {

  const [students, setStudents] =
    useState([]);

  const [loading, setLoading] =
    useState(true);

  const [searchTerm, setSearchTerm] =
    useState("");

  const [isModalOpen, setIsModalOpen] =
    useState(false);

  const [editingStudent, setEditingStudent] =
    useState(null);

  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {

    try {

      setLoading(true);

      const data =
        await getStudents();

      setStudents(data);

    } catch (error) {

      console.error(
        "Failed to load students",
        error
      );

    } finally {

      setLoading(false);

    }
  };

  const handleAddStudent = async (
    studentData
  ) => {

    try {

      await registerStudent(
        studentData
      );

      setIsModalOpen(false);

      await loadStudents();

      alert(
        "Student registered successfully"
      );

    } catch (error) {

      console.error(error);

      alert(
        "Failed to register student"
      );

    }
  };

  const handleUpdateStudent =
  async (studentData) => {

    try {

      const payload = {
        userId:
          editingStudent.userId,

        firstName:
          studentData.firstName,

        lastName:
          studentData.lastName,

        email:
          studentData.email,

        phone:
          studentData.phone,

        department:
          studentData.department,

        semester:
          Number(
            studentData.semester
          ),
      };

      await updateStudent(
        editingStudent.studentId,
        payload
      );

      setEditingStudent(null);

      setIsModalOpen(false);

      await loadStudents();

      alert(
        "Student updated successfully"
      );

    } catch (error) {

      console.error(
        error.response?.data ||
        error
      );

      alert(
        error.response?.data
          ?.message ||
          "Failed to update student"
      );

    }
  };

  const handleDeleteStudent =
    async (studentId) => {

      const confirmed =
        window.confirm(
          "Delete this student?"
        );

      if (!confirmed) return;

      try {

        await deleteStudent(
          studentId
        );

        await loadStudents();

        alert(
          "Student deleted successfully"
        );

      } catch (error) {

        console.error(error);

        alert(
          "Failed to delete student"
        );

      }
    };

  const openAddModal = () => {

    setEditingStudent(null);

    setIsModalOpen(true);

  };

  const openEditModal = (
    student
  ) => {

    setEditingStudent(student);

    setIsModalOpen(true);

  };

  const filteredStudents =
    useMemo(() => {

      const search =
        searchTerm.toLowerCase();

      return students.filter(
        (student) =>
          student.firstName
            ?.toLowerCase()
            .includes(search) ||
          student.lastName
            ?.toLowerCase()
            .includes(search) ||
          student.email
            ?.toLowerCase()
            .includes(search) ||
          String(
            student.userId
          ).includes(search)
      );

    }, [
      students,
      searchTerm,
    ]);

  return (
    <div className="space-y-6">

      <div className="flex items-center justify-between">

        <div>

          <h1 className="text-4xl font-bold text-[#2B2B2B]">
            Students
          </h1>

          <p className="text-gray-500 mt-2">
            Manage student records
          </p>

        </div>

        <button
          onClick={openAddModal}
          className="
            px-5
            py-3
            bg-[#2B2B2B]
            text-white
            rounded-xl
            hover:bg-[#3A3A3A]
            transition
          "
        >
          Register Student
        </button>

      </div>

      <div
        className="
          bg-white
          border
          border-[#D4D4D4]
          rounded-2xl
          shadow-sm
          p-6
        "
      >

        <input
          type="text"
          placeholder="Search by User ID, Name or Email..."
          value={searchTerm}
          onChange={(e) =>
            setSearchTerm(
              e.target.value
            )
          }
          className="
            w-full
            p-3
            rounded-xl
            border
            border-[#D4D4D4]
            mb-6
          "
        />

        {loading ? (

          <div className="text-center py-10">
            Loading students...
          </div>

        ) : filteredStudents.length ===
          0 ? (

          <div className="text-center py-10">
            No students found
          </div>

        ) : (

          <div className="overflow-x-auto">

            <table className="w-full">

              <thead>

                <tr className="border-b">

                  <th className="p-3 text-left">
                    Student ID
                  </th>

                  <th className="p-3 text-left">
                    User ID
                  </th>

                  <th className="p-3 text-left">
                    Name
                  </th>

                  <th className="p-3 text-left">
                    Email
                  </th>

                  <th className="p-3 text-left">
                    Department
                  </th>

                  <th className="p-3 text-left">
                    Semester
                  </th>

                  <th className="p-3 text-left">
                    Phone
                  </th>

                  <th className="p-3 text-left">
                    Actions
                  </th>

                </tr>

              </thead>

              <tbody>

                {filteredStudents.map(
                  (student) => (

                    <tr
                      key={
                        student.studentId
                      }
                      className="
                        border-b
                        hover:bg-gray-50
                      "
                    >

                      <td className="p-3">
                        {
                          student.studentId
                        }
                      </td>

                      <td className="p-3 font-semibold">
                        {
                          student.userId
                        }
                      </td>

                      <td className="p-3">
                        {
                          student.firstName
                        }{" "}
                        {
                          student.lastName
                        }
                      </td>

                      <td className="p-3">
                        {
                          student.email
                        }
                      </td>

                      <td className="p-3">
                        {
                          student.department
                        }
                      </td>

                      <td className="p-3">
                        {
                          student.semester
                        }
                      </td>

                      <td className="p-3">
                        {
                          student.phone
                        }
                      </td>

                      <td className="p-3">

                        <div className="flex gap-2">

                          <button
                            onClick={() =>
                              openEditModal(
                                student
                              )
                            }
                            className="
                              px-3
                              py-1
                              rounded-lg
                              bg-gray-300
                              hover:bg-gray-400
                            "
                          >
                            Edit
                          </button>

                          <button
                            onClick={() =>
                              handleDeleteStudent(
                                student.studentId
                              )
                            }
                            className="
                              px-3
                              py-1
                              rounded-lg
                              bg-[#2B2B2B]
                              text-white
                            "
                          >
                            Delete
                          </button>

                        </div>

                      </td>

                    </tr>

                  )
                )}

              </tbody>

            </table>

          </div>

        )}

      </div>

      <StudentModal
        isOpen={isModalOpen}
        onClose={() => {

          setIsModalOpen(false);

          setEditingStudent(null);

        }}
        onSubmit={
          editingStudent
            ? handleUpdateStudent
            : handleAddStudent
        }
        initialData={
          editingStudent
        }
      />

    </div>
  );
}

export default StudentsPage;