import { useEffect, useState } from "react";

function StudentModal({
  isOpen,
  onClose,
  onSubmit,
  initialData = null,
}) {
  const [formData, setFormData] = useState({
    userId: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    department: "",
    semester: "",
  });

  useEffect(() => {
    if (initialData) {
      setFormData(initialData);
    } else {
      setFormData({
        userId: "",
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        department: "",
        semester: "",
      });
    }
  }, [initialData]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const resetForm = () => {
  setFormData({
    userId: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    department: "",
    semester: "",
  });
};

const handleSubmit = async (e) => {
  e.preventDefault();

  await onSubmit(formData);

  resetForm();
};

  return (
    <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-50">

      <div className="bg-white w-full max-w-2xl rounded-2xl shadow-xl p-6">

        <h2 className="text-2xl font-bold text-[#2B2B2B] mb-6">
          {initialData ? "Edit Student" : "Add Student"}
        </h2>

        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-2 gap-4"
        >
          <input
            name="userId"
            placeholder="User ID"
            value={formData.userId}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3"
            required
          />

          <input
            name="semester"
            placeholder="Semester"
            value={formData.semester}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3"
            required
          />

          <input
            name="firstName"
            placeholder="First Name"
            value={formData.firstName}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3"
            required
          />

          <input
            name="lastName"
            placeholder="Last Name"
            value={formData.lastName}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3"
            required
          />

          <input
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3"
            required
          />

          <input
            name="phone"
            placeholder="Phone"
            value={formData.phone}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3"
            required
          />

          <input
            name="department"
            placeholder="Department"
            value={formData.department}
            onChange={handleChange}
            className="border border-[#D4D4D4] rounded-xl p-3 col-span-2"
            required
          />

          <div className="col-span-2 flex justify-end gap-3 mt-4">

            <button
              type="button"
              onClick={() => {
                    resetForm();
                    onClose();
                }}
              className="px-5 py-2 border border-[#D4D4D4] rounded-xl"
            >
              Cancel
            </button>

            <button
              type="submit"
              className="px-5 py-2 bg-[#2B2B2B] text-white rounded-xl"
            >
              Save
            </button>

          </div>
        </form>

      </div>

    </div>
  );
}

export default StudentModal;