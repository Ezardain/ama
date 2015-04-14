# Model Semester
# Table semesters
class Semester < ActiveRecord::Base
  has_many :activity_instances

  validates :name, presence: true, uniqueness: true
  validates :start_date, presence: true, uniqueness: true
  validates :end_date, presence: true, uniqueness: true

  accepts_nested_attributes_for :activity_instances
end
