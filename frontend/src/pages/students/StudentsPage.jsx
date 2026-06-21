import { useEffect, useMemo, useState } from "react";

import {
  getStudents,
  createStudent,
} from "../../api/studentApi";

import StudentModal from "../../components/students/StudentModal";

function StudentsPage() {
  const [students, setStudents] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [loading, setLoading] = useState(true);

  const [isModalOpen, setIsModalOpen] =
    useState(false);

  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {
    try {
      setLoading(true);

      const data = await getStudents();

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
      await createStudent(studentData);

      setIsModalOpen(false);

      await loadStudents();

      alert(
        "Student created successfully"
      );
    } catch (error) {
      console.error(error);

      alert(
        "Failed to create student"
      );
    }
  };

  const filteredStudents = useMemo(() => {
    return students.filter((student) => {
      const search =
        searchTerm.toLowerCase();

      return (
        student.firstName
          .toLowerCase()
          .includes(search) ||
        student.lastName
          .toLowerCase()
          .includes(search) ||
        student.email
          .toLowerCase()
          .includes(search)
      );
    });
  }, [students, searchTerm]);

  return (
    <div className="space-y-6">

      {/* Header */}

      <div className="flex items-center justify-between">

        <div>
          <h1 className="text-4xl font-bold text-[#2B2B2B]">
            Students
          </h1>

          <p className="text-gray-500 mt-2">
            Manage student records.
          </p>
        </div>

        <button
          onClick={() =>
            setIsModalOpen(true)
          }
          className="
            px-5
            py-3
            bg-[#2B2B2B]
            text-white
            rounded-xl
            hover:bg-[#3A3A3A]
            transition-all
            duration-200
          "
        >
          Add Student
        </button>

      </div>

      {/* Student Table Card */}

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

        {/* Search */}

        <input
          type="text"
          placeholder="Search by name or email..."
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
            focus:outline-none
            focus:ring-2
            focus:ring-[#2B2B2B]
            mb-6
          "
        />

        {/* Loading State */}

        {loading ? (
          <div className="text-center py-10 text-gray-500">
            Loading students...
          </div>
        ) : filteredStudents.length === 0 ? (
          <div className="text-center py-10 text-gray-500">
            No students found.
          </div>
        ) : (
          <div className="overflow-x-auto">

            <table className="w-full">

              <thead>

                <tr className="border-b border-[#D4D4D4]">

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
                    Student ID
                  </th>

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
                    Name
                  </th>

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
                    Email
                  </th>

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
                    Department
                  </th>

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
                    Semester
                  </th>

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
                    Phone
                  </th>

                  <th className="text-left py-4 px-3 font-semibold text-[#2B2B2B]">
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
                        border-[#EEEEEE]
                        hover:bg-[#F8F8F8]
                        transition
                      "
                    >

                      <td className="p-3">
                        {
                          student.studentId
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
                        {student.email}
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
                        {student.phone}
                      </td>

                      <td className="p-3">

                        <div className="flex gap-2">

                          <button
                            className="
                              px-3
                              py-1
                              rounded-lg
                              bg-[#D4D4D4]
                              hover:bg-[#B3B3B3]
                              transition
                            "
                          >
                            Edit
                          </button>

                          <button
                            className="
                              px-3
                              py-1
                              rounded-lg
                              bg-[#2B2B2B]
                              text-white
                              hover:bg-[#3A3A3A]
                              transition
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

      {/* Student Modal */}

      <StudentModal
        isOpen={isModalOpen}
        onClose={() =>
          setIsModalOpen(false)
        }
        onSubmit={
          handleAddStudent
        }
      />

    </div>
  );
}

export default StudentsPage;